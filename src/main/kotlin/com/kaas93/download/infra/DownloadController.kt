package com.kaas93.download.infra

import com.kaas93.download.model.DownloadRequest
import io.ktor.application.ApplicationCall
import io.ktor.response.respondFile
import org.kodein.di.LazyDI
import org.kodein.di.instance

class DownloadController(private val downloadService: DownloadService) {
  suspend fun handleDownload(request: DownloadRequest, call: ApplicationCall) {
    val file = downloadService.download(request)
    call.respondFile(file)
  }
}