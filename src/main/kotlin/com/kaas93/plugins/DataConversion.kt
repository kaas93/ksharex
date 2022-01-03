package com.kaas93.plugins

import io.ktor.application.*
import io.ktor.features.*
import java.util.*

fun Application.configureDataConversion() {
  install(DataConversion) {
    convert<UUID> {
      decode { values, _ -> values.singleOrNull()?.let { UUID.fromString(it)} }
      encode { value -> listOf(value.toString()) }
    }
  }
}
