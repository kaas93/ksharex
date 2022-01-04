package com.kaas93.download.infra

import com.kaas93.download.model.DownloadRequest
import com.kaas93.store.model.FileStore
import com.kaas93.upload.model.UploadStore
import io.ktor.features.*
import java.io.File

class DownloadService(private val dataStore: UploadStore, private val fileStore: FileStore) {
  fun download(request: DownloadRequest): File {
    val upload = dataStore.fetch(request.uuid) ?: throw NotFoundException()
    dataStore.save(upload.incrementDownloadCount())
    return fileStore.retrieve(upload.filename)
  }
}