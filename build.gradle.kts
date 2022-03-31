val ktorVersion: String by project
val kotlinVersion: String by project
val logbackVersion: String by project

plugins {
  application
  kotlin("jvm") version "1.6.10"
  id("org.jetbrains.kotlin.plugin.serialization") version "1.6.10"
}

group = "com.kaas93"
version = "0.0.1"
application {
  mainClassName = "io.ktor.server.netty.EngineMain"
}

repositories {
  mavenCentral()
}

dependencies {
  implementation("ch.qos.logback:logback-classic:$logbackVersion")
  implementation("com.aventrix.jnanoid:jnanoid:2.0.0")
  implementation("io.ktor:ktor-server-core:$ktorVersion")
  implementation("io.ktor:ktor-locations:$ktorVersion")
  implementation("io.ktor:ktor-serialization:$ktorVersion")
  implementation("io.ktor:ktor-server-netty:$ktorVersion")
  implementation("org.kodein.di:kodein-di-framework-ktor-server-jvm:7.11.0")
  implementation("org.litote.kmongo:kmongo-coroutine-serialization:4.5.0")

  testImplementation("io.ktor:ktor-server-tests:$ktorVersion")
  testImplementation("org.jetbrains.kotlin:kotlin-test-junit:$kotlinVersion")
}