package com.kaas93.store.infra

import com.kaas93.store.model.Store
import com.kaas93.store.model.StoreItem
import io.ktor.features.*
import java.util.UUID

class InMemoryStore<T : StoreItem> : Store<T> {
  private val storage = mutableMapOf<UUID, T>()

  override fun fetch(id: UUID): T? = storage[id]
  override fun save(item: T) {
    storage[item.id] = item
  }
  override fun delete(id: UUID) {
    if (!storage.containsKey(id)) throw NotFoundException()
    storage.remove(id)
  }
}