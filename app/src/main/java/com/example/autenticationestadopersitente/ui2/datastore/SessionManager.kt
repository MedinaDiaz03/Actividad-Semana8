package com.example.autenticationestadopersitente.ui2.datastore

import android.content.Context
import androidx.datastore.preferences.core.*
import androidx.datastore.preferences.preferencesDataStore
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

private val Context.dataStore by preferencesDataStore("session_prefs")

class SessionManager(private val context: Context) {

    companion object {
        val SESSION_ACTIVE = booleanPreferencesKey("session_active")
        val USER_EMAIL = stringPreferencesKey("user_email")
    }

    val sessionFlow: Flow<Boolean> =
        context.dataStore.data.map { prefs ->
            prefs[SESSION_ACTIVE] ?: false
        }

    suspend fun saveSession(email: String) {
        context.dataStore.edit { prefs ->
            prefs[SESSION_ACTIVE] = true
            prefs[USER_EMAIL] = email
        }
    }

    suspend fun clearSession() {
        context.dataStore.edit { prefs ->
            prefs.clear()
        }
    }

    val userEmailFlow: Flow<String> =
        context.dataStore.data.map { prefs ->
            prefs[USER_EMAIL] ?: ""
        }
}
