import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
	id("org.springframework.boot") version "3.0.2"
	id("io.spring.dependency-management") version "1.1.0"
	kotlin("jvm") version "1.7.22"
	kotlin("plugin.spring") version "1.7.22"
}

group = "io.board.kanban"
version = "0.0.1-SNAPSHOT"
java.sourceCompatibility = JavaVersion.VERSION_17

repositories {
	mavenCentral()
}

dependencies {
	implementation("com.fasterxml.jackson.module:jackson-module-kotlin")
	implementation("org.jetbrains.kotlin:kotlin-reflect")
	implementation("org.jetbrains.kotlin:kotlin-stdlib-jdk8")
	// Spring
	implementation("org.springframework.boot:spring-boot-starter-actuator")
	implementation("org.springframework.boot:spring-boot-starter-amqp")
	implementation("org.springframework.boot:spring-boot-starter-validation")
	implementation("org.springframework.boot:spring-boot-starter-web")
	implementation("org.springframework.data:spring-data-redis:3.0.1")
	// database
	implementation("org.springframework.boot:spring-boot-starter-data-mongodb")
	implementation("org.flywaydb:flyway-core")
	// logging
	implementation("io.github.microutils:kotlin-logging-jvm:3.0.4")
	// utils
	implementation("org.apache.commons:commons-lang3:3.12.0")
	implementation("org.springframework.cloud:spring-cloud-starter-openfeign:4.0.1")
	implementation("org.springframework.cloud:spring-cloud-starter-circuitbreaker-resilience4j:3.0.0")
	// tests
	testImplementation("org.junit.jupiter:junit-jupiter:5.8.1")
	testImplementation("io.mockk:mockk:1.13.2")
	testImplementation("org.assertj:assertj-core:3.24.2")
	testImplementation("org.springframework.boot:spring-boot-starter-test")
	testImplementation("org.springframework.amqp:spring-rabbit-test")
}

tasks.withType<KotlinCompile> {
	kotlinOptions {
		freeCompilerArgs = listOf("-Xjsr305=strict")
		jvmTarget = "17"
	}
}

tasks.withType<Test> {
	useJUnitPlatform()
	systemProperty("spring.profiles.active", "test")
}