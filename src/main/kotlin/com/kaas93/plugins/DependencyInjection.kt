package com.kaas93.plugins

import com.kaas93.auth.Auth
import com.kaas93.auth.AuthService
import com.kaas93.auth.AuthStore
import com.kaas93.download.infra.DownloadController
import com.kaas93.download.infra.DownloadService
import com.kaas93.store.infra.InMemoryStore
import com.kaas93.store.infra.LocalFileStore
import com.kaas93.store.infra.MongoStore
import com.kaas93.store.model.FileStore
import com.kaas93.upload.infra.UploadController
import com.kaas93.upload.infra.UploadService
import com.kaas93.upload.model.UploadStore
import io.ktor.application.Application
import kotlinx.coroutines.runBlocking
import org.kodein.di.bind
import org.kodein.di.ktor.di
import org.kodein.di.singleton
import org.litote.kmongo.coroutine.coroutine
import org.litote.kmongo.reactivestreams.KMongo

fun Application.configureDependencyInjection() {
  val client = KMongo.createClient("mongodb://sharex:sharex@localhost")
  val database = client.coroutine.getDatabase(environment.config.property("ktor.sharex.mongoDb").getString())

  val useMongo = "true" == environment.config.property("ktor.sharex.useMongo").getString()
  val uploadStore: UploadStore = if (useMongo) MongoStore.from(database) else InMemoryStore()
  val authStore: AuthStore = if (useMongo) MongoStore.from(database) else InMemoryStore()
  val localFileStore: FileStore = LocalFileStore()

  runBlocking {
    val auth = environment.config.property("ktor.sharex.apiKey").getString()
    authStore.save(Auth(auth))
  }

  val downloadService = DownloadService(uploadStore, localFileStore)
  val uploadService = UploadService(uploadStore, localFileStore)
  val authService = AuthService(authStore)

  di {
    bind { singleton { downloadService } }
    bind { singleton { uploadService } }
    bind { singleton { authService } }
    bind { singleton { UploadController(authService, uploadService) } }
    bind { singleton { DownloadController(downloadService) } }
  }
}