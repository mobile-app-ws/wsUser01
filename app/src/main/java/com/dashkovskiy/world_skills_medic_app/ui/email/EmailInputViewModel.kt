package com.dashkovskiy.world_skills_medic_app.ui.email

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.dashkovskiy.world_skills_medic_app.api.ApiService
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

//regex for email validation
private val emailRegex = "^[a-z0-9]+@[a-z0-9]+?\\.[a-z]{2,3}\$".toRegex()

data class SignInState(
    val email: String = ""
) {
    val isEmailValid: Boolean
        get() = emailRegex.matches(email)
}


class EmailInputViewModel : ViewModel() {
    private val _state = MutableStateFlow(SignInState())
    val viewState = _state.asStateFlow()

    private val state: SignInState
        get() = _state.value

    fun setEmail(email: String) {
        _state.value = _state.value.copy(email = email)
    }

}