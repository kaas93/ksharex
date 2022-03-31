package com.kaas93.store.infra

import com.kaas93.store.model.Store
import com.kaas93.store.model.StoreItem
import io.ktor.features.NotFoundException

class InMemoryStore<T : StoreItem> : Store<T> {
  private val storage = mutableMapOf<String, T>()

  override suspend fun fetch(id: String): T? = storage[id]
  override suspend fun save(item: T) {
    storage[item.id] = item
  }

  override suspend fun delete(id: String) {
    if (!storage.containsKey(id)) throw NotFoundException()
    storage.remove(id)
  }
}