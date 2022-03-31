package com.kaas93

import com.kaas93.plugins.*
import io.ktor.application.Application

fun main(args: Array<String>): Unit = io.ktor.server.netty.EngineMain.main(args)

fun Application.module() {
  configureDependencyInjection()
  configureRouting()
  configureHTTP()
  configureSerialization()
  configureStatusPages()
}
