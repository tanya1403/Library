package com.homefirst.Library.utils

import java.util.logging.Logger

object LoggerUtils {

    private val logger: Logger = Logger.getLogger(LoggerUtils::class.java.simpleName)

    fun log(value: String) {
        logger.info("\n\nHFops - Value --> $value\n\n")
    }

    fun logBody(body: String) {
        logger.info("\n\nHFops - Body --> $body\n\n")
    }

    fun logMethodCall(value: String) {
        logger.info("\nHFops -\n----------------------\n  Method --> $value  \n----------------------\n\n")
    }

    fun printLog(value: String) {
        println("\n\nHFops - Value --> $value\n\n")
    }

}