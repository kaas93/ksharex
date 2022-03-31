package com.kaas93.upload.model

import com.kaas93.store.model.StoreItem
import kotlinx.serialization.Contextual
import kotlinx.serialization.Serializable
import java.util.Date

@Serializable
data class Upload(
  val extension: String,
  val downloadCount: Int,
  val uploaded: @Contextual Date,
  val uploader: String
) : StoreItem() {
  val filename = "$id.$extension"

  fun incrementDownloadCount() = copy(downloadCount = downloadCount + 1)
}
