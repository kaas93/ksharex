package com.kaas93

import com.kaas93.download.infra.DownloadService
import com.kaas93.plugins.*
import com.kaas93.store.infra.InMemoryStore
import com.kaas93.store.infra.LocalFileStore
import com.kaas93.upload.UploadService
import com.kaas93.upload.model.Upload
import io.ktor.server.engine.*
import io.ktor.server.netty.*
import org.kodein.di.bind
import org.kodein.di.ktor.di
import org.kodein.di.singleton

val inMemoryUploadStore = InMemoryStore<Upload>()
val localFileStore = LocalFileStore()

fun main() {
  embeddedServer(Netty, port = 8080, host = "0.0.0.0") {
    di {
      bind { singleton { DownloadService(inMemoryUploadStore, localFileStore) } }
      bind { singleton { UploadService(inMemoryUploadStore, localFileStore) } }
    }

    configureDataConversion()
    configureRouting()
    configureSecurity()
    configureHTTP()
    configureSerialization()
  }.start(wait = true)
}
