package com.kaas93.download.infra

import com.kaas93.download.model.DownloadRequest
import io.ktor.application.Application
import io.ktor.application.ApplicationCall
import io.ktor.application.call
import io.ktor.locations.get
import io.ktor.response.respondFile
import io.ktor.routing.routing
import org.kodein.di.LazyDI
import org.kodein.di.instance
import org.kodein.di.ktor.closestDI

fun Application.addDownloadRouting() {
  val controller = DownloadController(closestDI())

  routing {
    get<DownloadRequest> { controller.handleDownload(it, call) }
  }
}

class DownloadController(di: LazyDI) {
  private val downloadService by di.instance<DownloadService>()

  suspend fun handleDownload(request: DownloadRequest, call: ApplicationCall) {
    val file = downloadService.download(request)
    call.respondFile(file)
  }
}