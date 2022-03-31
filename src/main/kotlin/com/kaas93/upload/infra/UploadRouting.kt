package com.kaas93.upload.infra

import io.ktor.application.Application
import io.ktor.application.call
import io.ktor.routing.post
import io.ktor.routing.routing
import org.kodein.di.instance
import org.kodein.di.ktor.closestDI

fun Application.addUploadRouting() {
  val controller by closestDI().instance<UploadController>()

  routing {
    post("/upload") {
      controller.handleUpload(call)
    }
  }
}

