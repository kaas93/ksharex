package com.kaas93.download.model

import io.ktor.locations.Location

@Location("/u/{uuid}")
data class DownloadRequest(
  val uuid: String
)

