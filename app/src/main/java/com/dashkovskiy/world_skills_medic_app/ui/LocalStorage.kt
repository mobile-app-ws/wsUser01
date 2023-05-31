package com.dashkovskiy.world_skills_medic_app.ui

import android.content.Context
import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.preferencesDataStore
import kotlinx.coroutines.flow.map

class LocalStorage(private val context: Context) {

    private val Context.dataStore by preferencesDataStore("local_storage")
    private val onboardFlagKey = booleanPreferencesKey("onboardFlag")

    fun onboardFinishFlag() = context.dataStore.data.map {
        it[onboardFlagKey] ?: false
    }


    suspend fun setOnboardFlag(flag: Boolean) {
        context.dataStore.edit { it[onboardFlagKey] = flag }
    }
}