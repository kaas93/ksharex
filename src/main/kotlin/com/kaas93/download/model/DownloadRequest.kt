package com.kaas93.download.model

import io.ktor.locations.Location
import kotlinx.serialization.Serializable
import java.util.UUID

@Serializable
@Location("/u/{uuid}")
data class DownloadRequest(
  @Serializable(with = UUIDSerializer::class)
  val uuid: UUID
)

