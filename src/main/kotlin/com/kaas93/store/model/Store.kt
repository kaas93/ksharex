package com.kaas93.store.model

import com.aventrix.jnanoid.jnanoid.NanoIdUtils
import kotlinx.serialization.SerialName

interface Store<T : StoreItem> {
  suspend fun fetch(id: String): T?
  suspend fun save(item: T)
  suspend fun delete(id: String)
}

abstract class StoreItem {
  @SerialName("_id")
  open val id: String = NanoIdUtils.randomNanoId()
}