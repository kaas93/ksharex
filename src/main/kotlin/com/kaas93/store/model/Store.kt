package com.kaas93.store.model

import java.util.*

interface Store<T : StoreItem> {
  fun fetch(id: UUID): T?
  fun save(item: T)
  fun delete(id: UUID)
}

interface StoreItem {
  val id: UUID
}