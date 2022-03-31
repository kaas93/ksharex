package com.kaas93.upload.infra

import com.kaas93.auth.AuthService
import com.kaas93.upload.UploadService
import io.ktor.application.Application
import io.ktor.application.call
import io.ktor.http.HttpStatusCode
import io.ktor.http.content.PartData
import io.ktor.http.content.readAllParts
import io.ktor.request.receiveMultipart
import io.ktor.response.respond
import io.ktor.routing.post
import io.ktor.routing.routing
import org.kodein.di.instance
import org.kodein.di.ktor.closestDI

fun Application.addUploadRouting() {
  routing {
    post("/upload") {
      val authService by closestDI().instance<AuthService>()
      val uploader = authService.authenticate(call)

      val parts = call.receiveMultipart().readAllParts()

      val uploadService by closestDI().instance<UploadService>()
      val uploads = parts.filterIsInstance<PartData.FileItem>().map { uploadService.handleUpload(it, uploader) }
      parts.forEach { it.dispose() }
      call.respond(HttpStatusCode.OK, uploads)
    }
  }
}