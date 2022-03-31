package com.kaas93.plugins

import com.aventrix.jnanoid.jnanoid.NanoIdUtils
import com.kaas93.auth.Auth
import com.kaas93.auth.AuthService
import com.kaas93.auth.AuthStore
import com.kaas93.download.infra.DownloadService
import com.kaas93.store.infra.InMemoryStore
import com.kaas93.store.infra.LocalFileStore
import com.kaas93.store.model.FileStore
import com.kaas93.upload.UploadService
import com.kaas93.upload.model.UploadStore
import io.ktor.application.Application
import org.kodein.di.bind
import org.kodein.di.ktor.di
import org.kodein.di.singleton

fun Application.configureDependencyInjection(
  inMemoryUploadStore: UploadStore = InMemoryStore(),
  inMemoryAuthStore: AuthStore = InMemoryStore(),
  localFileStore: FileStore = LocalFileStore()
) {
  val auth = NanoIdUtils.randomNanoId()
  println(auth)
  inMemoryAuthStore.save(Auth(auth))

  di {
    bind { singleton { DownloadService(inMemoryUploadStore, localFileStore) } }
    bind { singleton { UploadService(inMemoryUploadStore, localFileStore) } }
    bind { singleton { AuthService(inMemoryAuthStore) } }
  }
}