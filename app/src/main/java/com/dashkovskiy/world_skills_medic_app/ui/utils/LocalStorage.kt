package com.dashkovskiy.world_skills_medic_app.ui.utils

import android.content.Context
import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import kotlinx.coroutines.flow.map

class LocalStorage(private val context: Context) {

    private val Context.dataStore by preferencesDataStore("local_storage")
    private val onboardFlagKey = booleanPreferencesKey("onboardFlag")
    private val tokenKey = stringPreferencesKey("token")
    private val pwdKey = stringPreferencesKey("userPassword")

    fun onboardFinishFlag() = context.dataStore.data.map {
        it[onboardFlagKey] ?: false
    }

    fun userToken() = context.dataStore.data.map { it[tokenKey] ?: "" }

    fun userPassword() = context.dataStore.data.map { it[pwdKey] }

    suspend fun setOnboardFlag(flag: Boolean) {
        context.dataStore.edit { it[onboardFlagKey] = flag }
    }

    suspend fun setUserToken(token: String) {
        context.dataStore.edit { it[tokenKey] = token }
    }

    suspend fun setUserPassword(pwd : String){
        context.dataStore.edit { it[tokenKey] = pwd }
    }
}