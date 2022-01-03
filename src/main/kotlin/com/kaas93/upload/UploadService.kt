package com.kaas93.upload

import com.kaas93.store.model.FileStore
import com.kaas93.upload.model.Upload
import com.kaas93.upload.model.UploadStore
import io.ktor.http.content.*
import java.io.File
import java.util.*

class UploadService(private val dataStore: UploadStore, private val fileStore: FileStore) {
  fun handleUpload(uploadPart: PartData.FileItem): String {
    ensureUploadHasFileName(uploadPart)

    val upload = Upload(UUID.randomUUID(), uploadPart.originalFileName!!.extension(), 0, Date())
    dataStore.save(upload)
    fileStore.save(upload.filename, uploadPart.streamProvider())

    return upload.id.toString()
  }

  companion object {
    private fun ensureUploadHasFileName(uploadPart: PartData.FileItem) {
      requireNotNull(uploadPart.originalFileName)
    }
  }
}

private fun String.extension() = File(this).extension