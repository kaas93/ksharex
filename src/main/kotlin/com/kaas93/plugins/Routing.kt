package com.kaas93.plugins

import com.kaas93.download.infra.addDownloadRouting
import com.kaas93.upload.infra.addUploadRouting
import io.ktor.application.*
import io.ktor.locations.*

fun Application.configureRouting() {
  install(Locations) {}

  addDownloadRouting()
  addUploadRouting()
}

