package com.kaas93.download.infra

import com.kaas93.download.model.DownloadRequest
import io.ktor.application.*
import io.ktor.locations.*
import io.ktor.response.*
import io.ktor.routing.*
import org.kodein.di.instance
import org.kodein.di.ktor.closestDI

fun Application.addDownloadRouting() {
  routing {
    get<DownloadRequest> {
      val downloadService by closestDI().instance<DownloadService>()
      val file = downloadService.download(it)
      call.respondFile(file)
    }
  }
}