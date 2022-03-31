package com.kaas93.download.model

import io.ktor.locations.Location
import kotlinx.serialization.Serializable
import java.util.UUID

@Location("/u/{uuid}")
data class DownloadRequest(
  val uuid: String
)

