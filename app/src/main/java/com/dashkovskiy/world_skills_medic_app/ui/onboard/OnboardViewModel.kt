package com.dashkovskiy.world_skills_medic_app.ui.onboard

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.dashkovskiy.world_skills_medic_app.ui.utils.LocalStorage
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch


class OnboardViewModel(private val localStorage: LocalStorage) : ViewModel() {

    private val _boardState = MutableStateFlow(onboardItemsList)
    val boardState = _boardState.asStateFlow()


    fun saveOnboardFlag(){
        viewModelScope.launch {
            localStorage.setOnboardFlag(true)
        }
    }
}