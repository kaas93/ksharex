package com.kaas93.upload.infra

import com.kaas93.auth.AuthService
import io.ktor.application.ApplicationCall
import io.ktor.http.HttpStatusCode
import io.ktor.http.content.PartData
import io.ktor.http.content.readAllParts
import io.ktor.request.receiveMultipart
import io.ktor.response.respond

class UploadController(private val authService: AuthService, private val uploadService: UploadService) {
  suspend fun handleUpload(call: ApplicationCall) {
    val uploader = authService.authenticate(call)

    val parts = call.receiveMultipart().readAllParts()

    val uploads = parts.filterIsInstance<PartData.FileItem>().map { uploadService.handleUpload(it, uploader) }
    parts.forEach { it.dispose() }
    call.respond(HttpStatusCode.OK, uploads)
  }
}