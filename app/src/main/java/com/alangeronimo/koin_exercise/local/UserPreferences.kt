package com.alangeronimo.koin_exercise.local

import android.content.Context
import androidx.datastore.preferences.core.*
import androidx.datastore.preferences.preferencesDataStore
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

val Context.userDataStore by preferencesDataStore(name = "user_prefs")

class UserPreferences(private val context: Context) {

    companion object {
        val KEY_TOKEN = stringPreferencesKey("token")
        val KEY_USERNAME = stringPreferencesKey("username")
        val KEY_IS_LOGGED_IN = booleanPreferencesKey("is_logged_in")
    }

    // Flows que se pueden observar desde cualquier parte
    val token: Flow<String?> = context.userDataStore.data
        .map { prefs -> prefs[KEY_TOKEN] }

    val username: Flow<String?> = context.userDataStore.data
        .map { prefs -> prefs[KEY_USERNAME] }

    val isLoggedIn: Flow<Boolean> = context.userDataStore.data
        .map { prefs -> prefs[KEY_IS_LOGGED_IN] ?: false }

    suspend fun saveUserSession(token: String, username: String) {
        context.userDataStore.edit { prefs ->
            prefs[KEY_TOKEN] = token
            prefs[KEY_USERNAME] = username
            prefs[KEY_IS_LOGGED_IN] = true
        }
    }

    suspend fun clearSession() {
        context.userDataStore.edit { it.clear() }
    }
}