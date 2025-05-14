package com.homefirst.Library.security


import org.springframework.beans.factory.annotation.Autowired
import org.springframework.beans.factory.annotation.Value
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.mail.javamail.JavaMailSender
import org.springframework.mail.javamail.JavaMailSenderImpl
import org.springframework.scheduling.annotation.EnableAsync
import java.util.*

enum class EnvProfile(
    val value : String) {
    DEV("dev"),
    STAGING("staging"),
    UAT("uat"),
    PROD("prod")
}

@Configuration
@EnableAsync
class AppProperty(
//    @Autowired val credentialManager: CredsManager
) {

//    companion object {
//        private var _gDnrCred: Creds? = null
//    }


//    @Bean
//    fun getJavaMailSender(): JavaMailSender {
//
////        val gCred = _gDnrCred ?: gDnrCred()
//
//        val mailSender = JavaMailSenderImpl()
//        mailSender.host = "smtp.gmail.com"
//        mailSender.port = 587
//        mailSender.username = gCred?.username
//        mailSender.password = gCred?.password
//
//        val props: Properties = mailSender.javaMailProperties
//        props["mail.transport.protocol"] = "smtp"
//        props["mail.smtp.auth"] = true
//        props["mail.smtp.starttls.enable"] = true
//        props["mail.debug"] = true
//
//        return mailSender
//
//    }

    @Value("\${spring.profiles.active}")
    lateinit var activeProfile : String

    @Value("\${application.flags.isStrictProduction}")
    var isStrictProduction : Boolean = false

    fun isProduction() = activeProfile == EnvProfile.PROD.value

    fun isUAT() = activeProfile == EnvProfile.UAT.value

    fun isStaging() = activeProfile == EnvProfile.DEV.value

    @Value("\${application.key.salt}")
    lateinit var salt: String

    @Value("\${application.flags.isSalesforceLive}")
    var isSalesforceLive: Boolean = false

    @Value("\${application.key.mamasSpaghetti}")
    lateinit var  mamasSpaghetti: String

    @Value("\${application.key.mamasSalt}")
    lateinit var  mamasSalt: String

//    @Value("\${application.s3Bucket.name}")
//    lateinit var s3BucketName: String
//
//    @Value("\${application.s3Bucket.region}")
//    lateinit var s3BucketRegion: String
//
//    @Value("\${application.path.preDataBackup}")
//    lateinit var preDataBackup: String
//
//    @Value("\${application.path.localDataStorage}")
//    lateinit var localDataStorage: String
//
//    @Value("\${application.path.bulkUpload}")
//    lateinit var bulkUploadLPath: String
//
//    @Value("\${application.path.originalFilePath}")
//    lateinit var originalFilePath: String

    @Value("\${application.path.credentials}")
    lateinit var credentials: String


//    @Value("\${spring.mail.username}")
//    lateinit var senderEmail: String


}
