package org.sopt.at.data.datasource

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class UserPreferencesDataSource @Inject constructor(
    private val dataStore: DataStore<Preferences>,
) {
    companion object {
        private val KEY_USER_ID = stringPreferencesKey("user_id")
        private val KEY_USER_PASSWORD = stringPreferencesKey("user_password")
        private val KEY_IS_LOGGED_IN = booleanPreferencesKey("is_logged_in")

        // TODO: 2주차 과제용 임시 키
        private val KEY_TEMP_USER_ID = stringPreferencesKey("temp_user_id")
        private val KEY_TEMP_USER_PASSWORD = stringPreferencesKey("temp_user_password")
    }

    suspend fun saveTempUserCredentials(userId: String, password: String) {
        dataStore.edit { preferences ->
            preferences[KEY_TEMP_USER_ID] = userId
            preferences[KEY_TEMP_USER_PASSWORD] = password
            preferences[KEY_IS_LOGGED_IN] = true
        }
    }

    val getTempUserCredentials: Flow<Pair<String, String>> = dataStore.data.map { preferences ->
        val userId = preferences[KEY_TEMP_USER_ID].orEmpty()
        val password = preferences[KEY_TEMP_USER_PASSWORD].orEmpty()
        Pair(userId, password)
    }

    suspend fun saveUserCredentials(userId: String, password: String) {
        dataStore.edit { preferences ->
            preferences[KEY_USER_ID] = userId
            preferences[KEY_USER_PASSWORD] = password
        }
    }

    val getUserCredentials: Flow<Pair<String, String>> = dataStore.data.map { preferences ->
        val userId = preferences[KEY_USER_ID].orEmpty()
        val password = preferences[KEY_USER_PASSWORD].orEmpty()
        Pair(userId, password)
    }

    val isLoggedIn: Flow<Boolean> = dataStore.data.map { preferences ->
        preferences[KEY_IS_LOGGED_IN] == true
    }

    suspend fun clearUserData() {
        dataStore.edit { preferences ->
            preferences.remove(KEY_USER_ID)
            preferences.remove(KEY_USER_PASSWORD)
            preferences[KEY_IS_LOGGED_IN] = false
        }
    }
}