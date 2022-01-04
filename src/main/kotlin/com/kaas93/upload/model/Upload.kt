package com.kaas93.upload.model

import com.kaas93.store.model.StoreItem
import java.util.*

data class Upload(
  override val id: UUID, val extension: String, val downloadCount: Int, val uploaded: Date
) : StoreItem {

  val filename = "$id.$extension"

  fun incrementDownloadCount() = copy(downloadCount = downloadCount + 1)
}
