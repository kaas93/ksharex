package com.kaas93.store.infra

import com.kaas93.store.model.Store
import com.kaas93.store.model.StoreItem
import org.litote.kmongo.coroutine.CoroutineCollection
import org.litote.kmongo.coroutine.CoroutineDatabase
import org.litote.kmongo.eq

class MongoStore<T : StoreItem> constructor(private val collection: CoroutineCollection<T>) : Store<T> {
  companion object {
    inline fun <reified T : StoreItem> from(database: CoroutineDatabase): MongoStore<T> {
      val collection = database.getCollection<T>()
      return MongoStore(collection)
    }
  }

  override suspend fun fetch(id: String): T? {
    return collection.findOneById(id)
  }

  override suspend fun save(item: T) {
    collection.save(item)
  }

  override suspend fun delete(id: String) {
    collection.deleteOneById(id)
  }
}