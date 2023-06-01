package com.dashkovskiy.world_skills_medic_app.ui.password

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.dashkovskiy.world_skills_medic_app.ui.utils.LocalStorage
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

data class CreatePasswordState(
    val pwd : String = "",
    val isPasswordSaved : Boolean = false
)

class CreatePasswordViewModel(
    private val localStorage: LocalStorage
) : ViewModel() {

    private val _state = MutableStateFlow(CreatePasswordState())
    val viewState = _state.asStateFlow()

    private val state: CreatePasswordState
        get() = _state.value

    fun setPassword(number : Int){
        val pwd = state.pwd + number
        if (pwd.length <= 4){
            _state.value = state.copy(pwd = pwd)
        }
        if (pwd.length == 4){
            viewModelScope.launch {
                localStorage.setUserPassword(state.pwd)
            }
        }
    }

    fun setPasswordSaved(isSaved : Boolean = false){
        _state.value = state.copy(isPasswordSaved = isSaved)
    }

    fun deleteNumber(){
        _state.value = state.copy(pwd = state.pwd.dropLast(1))
    }
}