package com.kaas93.auth

import com.kaas93.store.model.Store
import com.kaas93.store.model.StoreItem
import io.ktor.application.ApplicationCall
import javax.naming.AuthenticationException

typealias AuthStore = Store<Auth>

class AuthService(private val authStore: AuthStore) {
  fun authenticate(call: ApplicationCall): String {
    val secret = call.request.queryParameters["secret"]
    requireNotNull(secret)
    authenticate(secret)
    return secret
  }

  private fun authenticate(secret: String) {
    if (authStore.fetch(secret) == null) throw AuthenticationException("Invalid secret!")
  }
}

data class Auth(override val id: String) : StoreItem