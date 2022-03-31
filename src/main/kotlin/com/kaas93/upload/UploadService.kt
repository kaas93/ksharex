package com.kaas93.upload

import com.kaas93.store.model.FileStore
import com.kaas93.upload.model.Upload
import com.kaas93.upload.model.UploadStore
import io.ktor.http.content.PartData
import io.ktor.http.content.streamProvider
import java.io.File
import java.util.Date

class UploadService(private val dataStore: UploadStore, private val fileStore: FileStore) {
  suspend fun handleUpload(uploadPart: PartData.FileItem, uploader: String): String {
    ensureUploadHasFileName(uploadPart)

    val upload = Upload(uploadPart.originalFileName!!.extension(), 0, Date(), uploader)
    dataStore.save(upload)
    fileStore.save(upload.filename, uploadPart.streamProvider())

    return upload.id
  }

  companion object {
    private fun ensureUploadHasFileName(uploadPart: PartData.FileItem) {
      requireNotNull(uploadPart.originalFileName)
    }
  }
}

private fun String.extension() = File(this).extension