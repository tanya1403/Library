package com.homefirst.Library

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.autoconfigure.domain.EntityScan
import org.springframework.boot.runApplication
import org.springframework.context.annotation.ComponentScan
import org.springframework.data.jpa.repository.config.EnableJpaRepositories

@SpringBootApplication
@EnableJpaRepositories(basePackages = ["com.homefirst.Library.repository"])
@EntityScan(basePackages = ["com.homefirst.Library.entity"])
@ComponentScan(basePackages = ["com.homefirst.Library"])
class LibraryApplication

fun main(args: Array<String>) {
	runApplication<LibraryApplication>(*args)
}
