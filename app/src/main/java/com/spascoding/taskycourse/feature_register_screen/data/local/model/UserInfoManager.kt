package com.spascoding.taskycourse.feature_register_screen.data.local.model

import android.content.Context
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.longPreferencesKey
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class UserInfoManager @Inject constructor(private val context: Context) {

    private val Context.dataStore by preferencesDataStore("user_prefs")

    companion object {
        private val EMAIL_KEY = stringPreferencesKey("email")
        private val PASSWORD_KEY = stringPreferencesKey("password")
        private val ACCESS_TOKEN_KEY = stringPreferencesKey("access_token")
        private val REFRESH_TOKEN_KEY = stringPreferencesKey("refresh_token")
        private val FULL_NAME_KEY = stringPreferencesKey("full_name")
        private val USER_ID_KEY = stringPreferencesKey("user_id")
        private val ACCESS_TOKEN_EXPIRATION_KEY = longPreferencesKey("access_token_expiration")
    }

    suspend fun saveUserInfo(userInfo: UserInfo) {
        context.dataStore.edit { preferences ->
            preferences[EMAIL_KEY] = userInfo.email
            preferences[PASSWORD_KEY] = userInfo.password
            preferences[ACCESS_TOKEN_KEY] = userInfo.accessToken
            preferences[REFRESH_TOKEN_KEY] = userInfo.refreshToken
            preferences[FULL_NAME_KEY] = userInfo.fullName
            preferences[USER_ID_KEY] = userInfo.userId
            preferences[ACCESS_TOKEN_EXPIRATION_KEY] = userInfo.accessTokenExpirationTimestamp
        }
    }

    val userInfoFlow: Flow<UserInfo> = context.dataStore.data.map { preferences ->
        UserInfo(
            email = preferences[EMAIL_KEY] ?: "",
            password = preferences[PASSWORD_KEY] ?: "",
            accessToken = preferences[ACCESS_TOKEN_KEY] ?: "",
            refreshToken = preferences[REFRESH_TOKEN_KEY] ?: "",
            fullName = preferences[FULL_NAME_KEY] ?: "",
            userId = preferences[USER_ID_KEY] ?: "",
            accessTokenExpirationTimestamp = preferences[ACCESS_TOKEN_EXPIRATION_KEY] ?: 0L
        )
    }

    suspend fun clearUserInfo() {
        context.dataStore.edit { preferences ->
            preferences.clear()
        }
    }
}