package com.kaas93.plugins

import io.ktor.application.Application
import io.ktor.application.install
import io.ktor.features.StatusPages
import io.ktor.http.HttpStatusCode
import io.ktor.response.respond
import javax.naming.AuthenticationException

fun Application.configureStatusPages() {
  install(StatusPages) {
    exception<AuthenticationException> { cause ->
      context.respond(HttpStatusCode.Unauthorized)
    }
  }
}