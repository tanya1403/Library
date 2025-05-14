package com.homefirst.Library.utils



import com.google.api.services.gmail.Gmail
import jakarta.activation.DataHandler
import jakarta.activation.FileDataSource
import jakarta.mail.Message
import jakarta.mail.Session
import jakarta.mail.internet.InternetAddress
import jakarta.mail.internet.MimeBodyPart
import jakarta.mail.internet.MimeMessage
import jakarta.mail.internet.MimeMultipart
import org.springframework.stereotype.Component

import java.io.ByteArrayOutputStream
import java.io.File
import java.util.*


@Component
class EmailUtils(
    private val gmail: Gmail
) {

    fun sendEmail(
        to: String,
        subject: String,
        bodyText: String,
        cc: List<String> = emptyList()
    ) {
        val mimeMessage = createEmail(to, subject, bodyText, cc)
        val customMessage = createMessageWithEmail(mimeMessage)
        val gmailMessage = com.google.api.services.gmail.model.Message()
        gmailMessage.raw = customMessage.raw
        try {
            val sentMessage = gmail.users().messages().send("me", gmailMessage).execute()
            println("Email sent successfully. : ${sentMessage.id}")
        } catch (e: Exception) {
            println("Failed to send email: ${e.message}")
            e.printStackTrace()
        }

    }

    fun sendEmailWithAttachment(
        to: String,
        subject: String,
        bodyText: String,
        attachmentFile: File,
        cc: List<String> = emptyList()
    ) {
        val mimeMessage = createEmailWithAttachment(to, subject, bodyText, attachmentFile, cc)
        val customMessage = createMessageWithEmail(mimeMessage)
        val gmailMessage = com.google.api.services.gmail.model.Message()
        gmailMessage.raw = customMessage.raw
        try {
            val sentMessage = gmail.users().messages().send("me", gmailMessage).execute()
            println("sendEmailWithAttachment Email sent successfully. : ${sentMessage.id}")
        } catch (e: Exception) {
            println("Failed to send email: ${e.message}")
            e.printStackTrace()
        }
    }

    fun createEmail(
        to: String,
        subject: String,
        bodyText: String,
        cc: List<String> = emptyList()
    ): MimeMessage {
        val props = Properties()
        val session = Session.getDefaultInstance(props, null)

        val email = MimeMessage(session)
        email.addRecipient(Message.RecipientType.TO, InternetAddress(to))
        cc.forEach { email.addRecipient(Message.RecipientType.CC, InternetAddress(it)) }
        email.subject = subject
        email.setText(appendSignature(bodyText))
        return email
    }

    fun createEmailWithAttachment(
        to: String,
        subject: String,
        bodyText: String,
        attachmentFile: File,
        cc: List<String> = emptyList()
    ): MimeMessage {
        val props = Properties()
        val session = Session.getDefaultInstance(props, null)

        val email = MimeMessage(session)
        email.addRecipient(Message.RecipientType.TO, InternetAddress(to))
        cc.forEach { email.addRecipient(Message.RecipientType.CC, InternetAddress(it)) }

        email.subject = subject

        val multipart = MimeMultipart()

        val textPart = MimeBodyPart()
        textPart.setText(appendSignature(bodyText))
        multipart.addBodyPart(textPart)

        val attachmentPart = MimeBodyPart()
        val fileDataSource = FileDataSource(attachmentFile)
        attachmentPart.dataHandler = DataHandler(fileDataSource)
        attachmentPart.fileName = attachmentFile.name
        attachmentPart.setHeader("Content-Type", "application/octet-stream")
        multipart.addBodyPart(attachmentPart)

        email.setContent(multipart)

        return email
    }

    fun createMessageWithEmail(emailContent: MimeMessage): GmailMessage {
        val buffer = ByteArrayOutputStream()
        emailContent.writeTo(buffer)
        val rawEmail = Base64.getUrlEncoder().withoutPadding().encodeToString(buffer.toByteArray())
        return GmailMessage().apply { raw = rawEmail }

    }

    fun appendSignature(bodyText: String): String {
        return """
Hi,

$bodyText

Regards,
Homefirst IT Team
""".trimIndent()
    }

}
class GmailMessage {
    var raw: String? = null
}
