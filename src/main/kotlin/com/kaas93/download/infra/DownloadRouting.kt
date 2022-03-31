package com.kaas93.download.infra

import com.kaas93.download.model.DownloadRequest
import io.ktor.application.Application
import io.ktor.application.call
import io.ktor.locations.get
import io.ktor.routing.routing
import org.kodein.di.instance
import org.kodein.di.ktor.closestDI

fun Application.addDownloadRouting() {
  val controller by closestDI().instance<DownloadController>()

  routing {
    get<DownloadRequest> { controller.handleDownload(it, call) }
  }
}

