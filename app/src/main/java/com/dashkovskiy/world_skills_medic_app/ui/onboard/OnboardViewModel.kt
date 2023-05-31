package com.dashkovskiy.world_skills_medic_app.ui.onboard

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow

data class OnboardState(
    val onboardQueue : List<OnboardItem> = emptyList(),

)

class OnboardViewModel : ViewModel() {
    private val _boardState = MutableStateFlow< List<OnboardItem>>(emptyList())
    val boardState = _boardState.asStateFlow()

    fun dropOnboard(){
        //todo
    }

    fun saveOnboardFlag(){
        //todo
    }
}