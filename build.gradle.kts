plugins {
	kotlin("jvm") version "1.9.25"
	kotlin("plugin.spring") version "1.9.25"
	id("org.springframework.boot") version "3.4.5"
	id("io.spring.dependency-management") version "1.1.7"
}

group = "com.homefirst.Library"
version = "0.0.1-SNAPSHOT"

java {
	withSourcesJar()
}

repositories {
	mavenCentral()
}

dependencies {
	implementation("org.springframework.boot:spring-boot-starter-oauth2-client")
	implementation("org.springframework.boot:spring-boot-starter-security")
	implementation("org.springframework.boot:spring-boot-starter-web")
	implementation("org.springframework.boot:spring-boot-starter-web-services")
	implementation("com.fasterxml.jackson.module:jackson-module-kotlin")
	implementation("org.jetbrains.kotlin:kotlin-reflect")
	compileOnly("org.projectlombok:lombok")
	runtimeOnly("com.mysql:mysql-connector-j")
	annotationProcessor("org.projectlombok:lombok")
	testImplementation("org.springframework.boot:spring-boot-starter-test")
	testImplementation("org.jetbrains.kotlin:kotlin-test-junit5")
	testImplementation("org.springframework.security:spring-security-test")
	testRuntimeOnly("org.junit.platform:junit-platform-launcher")
//	implementation("javax.persistence:javax.persistence-api:2.2")
	implementation("org.apache.httpcomponents:httpclient:4.5.14")
	implementation("org.springframework.boot:spring-boot-starter-data-jpa")
	implementation("org.springframework.boot:spring-boot-starter-security")
	implementation("org.springframework.boot:spring-boot-starter-web")
	implementation("org.jetbrains.kotlin:kotlin-reflect")
	implementation("org.jetbrains.kotlin:kotlin-stdlib-jdk8")
	developmentOnly("org.springframework.boot:spring-boot-devtools")
	implementation("org.json:json:20230227")
	compileOnly("org.projectlombok:lombok:0.11.0")
	implementation("org.apache.commons:commons-lang3:3.12.0")
	implementation("commons-codec:commons-codec:1.15")
	implementation("commons-io:commons-io:2.11.0")
	implementation("com.squareup.okhttp3:okhttp:4.10.0")
	implementation("org.apache.tika:tika-core:2.5.0")
	implementation("org.apache.httpcomponents:httpclient:4.5.13")
	implementation("com.fasterxml.jackson.core:jackson-core:2.13.4")
	implementation("com.opencsv:opencsv:5.7.1")
	implementation("org.springframework.boot:spring-boot-starter-mail:2.7.5")
	implementation("com.amazonaws:aws-java-sdk-s3:1.12.452")
	implementation("com.itextpdf:kernel:7.2.3")
	implementation("com.itextpdf:layout:7.2.3")
	implementation("net.ttddyy:datasource-proxy:1.9")
	implementation("com.nimbusds:nimbus-jose-jwt:9.31")
	implementation("org.springframework.boot:spring-boot-starter-web")
	implementation("org.apache.httpcomponents:httpclient:4.5.14")
	implementation("jakarta.xml.bind:jakarta.xml.bind-api:4.0.0")
	implementation("org.glassfish.jaxb:jaxb-runtime:4.0.2")
	implementation("org.springframework.boot:spring-boot-starter-mail")
	implementation("com.sun.mail:jakarta.mail:2.0.1")
	implementation("org.jetbrains.kotlinx:kotlinx-coroutines-core:1.7.3")
	implementation("org.jetbrains.kotlinx:kotlinx-coroutines-jdk8:1.7.3")
	implementation("org.apache.camel.springboot:camel-spring-boot-starter:4.4.0")
	implementation("org.apache.camel.springboot:camel-http-starter:4.4.0")
	implementation("org.apache.camel.springboot:camel-jackson-starter:4.4.0")
	implementation("org.apache.camel:camel-kotlin-dsl:4.4.0")
	implementation("org.springframework.boot:spring-boot-starter-web")
	implementation("mysql:mysql-connector-java:8.0.33")
	implementation("jakarta.persistence:jakarta.persistence-api:3.1.0")
	implementation("org.hibernate.orm:hibernate-core:6.2.7.Final")
	testImplementation("org.springframework.boot:spring-boot-starter-test")
	testImplementation("org.springframework.security:spring-security-test")
	runtimeOnly("mysql:mysql-connector-java:8.0.33")

}

kotlin {
	compilerOptions {
		freeCompilerArgs.addAll("-Xjsr305=strict")
	}
}

tasks.withType<Test> {
	useJUnitPlatform()
}

tasks.jar {
	manifest {
		attributes(
			"Implementation-Title" to project.name,
			"Implementation-Version" to project.version
		)
	}
}

tasks.jar {
	enabled = true
	archiveClassifier.set("")
}

tasks.bootJar {
	enabled = false
}


