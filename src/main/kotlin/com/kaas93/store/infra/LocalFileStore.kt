package com.kaas93.store.infra

import com.kaas93.store.model.FileStore
import java.io.File
import java.io.FileNotFoundException
import java.io.InputStream

class LocalFileStore : FileStore {
  override fun save(path: String, input: InputStream) {
    ensureUploadFolderExists()
    val destination = upload(path)
    destination.writeBytes(input.readBytes())
    input.close()
  }

  override fun retrieve(path: String): File {
    val file = upload(path)
    if (!file.exists()) throw FileNotFoundException()
    return file
  }

  override fun delete(path: String) {
    retrieve(path).delete()
  }

  companion object {
    private fun ensureUploadFolderExists() {
      File("uploads").mkdirs()
    }

    private fun upload(path: String) = File("uploads/$path")
  }
}
