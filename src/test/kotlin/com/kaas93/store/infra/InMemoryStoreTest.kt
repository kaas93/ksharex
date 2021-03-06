package com.kaas93.store.infra

import com.kaas93.store.model.StoreItem
import io.ktor.features.*
import kotlinx.coroutines.runBlocking
import java.util.*
import java.util.UUID.randomUUID
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertFailsWith
import kotlin.test.assertNull

internal class InMemoryStoreTest {
  @Test
  fun `should return null for id if no item is stored`() {
    val store = InMemoryStore<StoreItem>()
    runBlocking {
      assertNull(store.fetch("some-non-existent-id"))
    }
  }

  @Test
  fun `should store and return item for id`() {
    val store = InMemoryStore<SimpleStoreItem>()
    val item = SimpleStoreItem("some-id")
    runBlocking {
      store.save(item)
      assertEquals(item, store.fetch(item.id))
    }
  }

  @Test
  fun `should fail to delete item for non-existent id`() {
    val store = InMemoryStore<StoreItem>()
    runBlocking {
      assertFailsWith<NotFoundException> { store.delete("some-non-existent-id") }
    }
  }

  @Test
  fun `should no longer return deleted item`() {
    val store = InMemoryStore<SimpleStoreItem>()
    val item = SimpleStoreItem("some-id")
    runBlocking {
      store.save(item)
      assertEquals(item, store.fetch(item.id))
      store.delete(item.id)
      assertNull(store.fetch(item.id))
    }
  }

  data class SimpleStoreItem(override val id: String) : StoreItem()
}