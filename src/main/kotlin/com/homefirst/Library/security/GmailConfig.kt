package com.homefirst.Library.security
import com.google.api.client.extensions.java6.auth.oauth2.AuthorizationCodeInstalledApp
import com.google.api.client.extensions.jetty.auth.oauth2.LocalServerReceiver
import com.google.api.client.googleapis.javanet.GoogleNetHttpTransport
import com.google.api.client.json.jackson2.JacksonFactory
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import java.io.File
import java.io.FileReader
import com.google.api.services.gmail.Gmail
import com.google.api.services.gmail.GmailScopes
import com.google.api.client.json.JsonFactory
import com.google.api.client.util.store.FileDataStoreFactory
import com.google.api.client.googleapis.auth.oauth2.GoogleAuthorizationCodeFlow
import com.google.api.client.googleapis.auth.oauth2.GoogleClientSecrets
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.core.io.ClassPathResource
import java.io.FileNotFoundException
import java.io.InputStreamReader

@Configuration
class GmailConfig @Autowired constructor(
    val appProperty: AppProperty
) {
//    private val TOKENS_DIRECTORY_PATH = "/app/tokens"
    private val TOKENS_DIRECTORY_PATH = "tokens"

    private fun getGmailService(): Gmail {
        val jsonFactory: JsonFactory = JacksonFactory.getDefaultInstance()
        val httpTransport = GoogleNetHttpTransport.newTrustedTransport()

        // Load client secrets using ClassPathResource
        val resource = ClassPathResource(appProperty.credentials)
        if (!resource.exists()) {
            throw FileNotFoundException("Credentials file not found at classpath: ${appProperty.credentials}")
        }

        val reader = InputStreamReader(resource.inputStream)
        val clientSecrets = GoogleClientSecrets.load(jsonFactory, reader)

        if (!resource.exists()) {
            throw IllegalStateException("Credentials file not found in classpath: ${appProperty.credentials}")
        }

        val flow = GoogleAuthorizationCodeFlow.Builder(
            httpTransport, jsonFactory, clientSecrets,
            listOf(GmailScopes.GMAIL_SEND)
        )
            .setDataStoreFactory(FileDataStoreFactory(File(TOKENS_DIRECTORY_PATH)))
            .setAccessType("offline")
            .build()

//        val credential = flow.loadCredential("user")
//            ?: throw IllegalStateException("No stored credential for user")

        val receiver = LocalServerReceiver.Builder().setPort(8888).build()
        val credentials = AuthorizationCodeInstalledApp(flow, receiver).authorize("user")


        return Gmail.Builder(httpTransport, jsonFactory, credentials)
            .setApplicationName("My Gmail App")
            .build()
    }

    @Bean
    fun gmailService(): Gmail {
        return try {
            getGmailService()
        } catch (e: Exception) {
            println("Failed to initialize Gmail service: ${e.message}")
            throw RuntimeException("Cannot create Gmail service bean", e)
        }
    }
}










