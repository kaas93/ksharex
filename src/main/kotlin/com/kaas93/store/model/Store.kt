package com.kaas93.store.model

interface Store<T : StoreItem> {
  fun fetch(id: String): T?
  fun save(item: T)
  fun delete(id: String)
}

interface StoreItem {
  val id: String
}