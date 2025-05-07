package org.sopt.at.data.datasource

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.intPreferencesKey
import kotlinx.coroutines.flow.firstOrNull
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class TokenDataStore @Inject constructor(
    private val preferenceDataStore: DataStore<Preferences>
) {
    suspend fun getUserId(): Int? = preferenceDataStore.data.map { preferences ->
        preferences[preferencesTokenKey]
    }.firstOrNull()

    suspend fun setUserId(userId: Int) {
        preferenceDataStore.edit { preferences ->
            preferences[preferencesTokenKey] = userId
        }
    }

    suspend fun clearInfo() {
        preferenceDataStore.edit { preferences ->
            preferences.remove(preferencesTokenKey)
        }
    }

    companion object {
        private const val TOKEN_KEY = "token_key"
        private val preferencesTokenKey = intPreferencesKey(TOKEN_KEY)
    }
}
