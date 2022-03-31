package com.kaas93.upload.infra

import com.kaas93.auth.AuthService
import com.kaas93.upload.UploadService
import io.ktor.application.*
import io.ktor.http.*
import io.ktor.http.content.*
import io.ktor.request.*
import io.ktor.response.*
import io.ktor.routing.*
import org.kodein.di.instance
import org.kodein.di.ktor.closestDI

fun Application.addUploadRouting() {
  routing {
    post("/upload") {
      val authService by closestDI().instance<AuthService>()
      authService.authenticate(call)

      val parts = call.receiveMultipart().readAllParts()

      val uploadService by closestDI().instance<UploadService>()
      val uploads = parts.filterIsInstance<PartData.FileItem>().map(uploadService::handleUpload)
      parts.forEach { it.dispose() }
      call.respond(HttpStatusCode.OK, uploads)
    }
  }
}