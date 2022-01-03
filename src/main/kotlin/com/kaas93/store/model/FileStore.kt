package com.kaas93.store.model

import java.io.File
import java.io.InputStream

interface FileStore {
  fun save(path: String, stream: InputStream)
  fun retrieve(path: String): File
  fun delete(path: String)
}
