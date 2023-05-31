package com.dashkovskiy.world_skills_medic_app.ui.code

import android.os.CountDownTimer
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.dashkovskiy.world_skills_medic_app.api.ApiService
import com.dashkovskiy.world_skills_medic_app.ui.utils.LocalStorage
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import kotlin.time.Duration.Companion.milliseconds

data class CodeInputState(
    val code: String = "",
    val time: String = "",
    val isSignInSuccessful: Boolean = false
)

class CodeInputViewModel(
    private val apiService: ApiService,
    private val localStorage: LocalStorage,
    private val email: String
) : ViewModel() {
    private val _state = MutableStateFlow(CodeInputState())
    val viewState = _state.asStateFlow()

    private var timer: CountDownTimer? = null

    private val state: CodeInputState
        get() = _state.value

    init {
        sendCode()
        startTimer()
    }

    private fun startTimer() {
        timer = object : CountDownTimer(60000L, 1000L) {
            override fun onTick(millisUntilFinished: Long) {
                _state.value = state.copy(
                    time = millisUntilFinished.milliseconds.inWholeSeconds.toString()
                )
            }
            override fun onFinish() {
                startTimer()
                sendCode()
            }
        }.start()
    }

    fun setCode(code: String) {
        _state.value = _state.value.copy(code = code)
        if (code.length == 4) {
            signIn()
        }
    }

    private fun signIn() {
        viewModelScope.launch {
            val response = apiService.signIn(email = email, code = state.code)
            if (response.isSuccessful) {
                val body = response.body() ?: return@launch
                localStorage.setUserToken(token = body.token)
                _state.value = state.copy(isSignInSuccessful = true)
            }
        }
    }

    private fun sendCode() {
        viewModelScope.launch {
            apiService.sendCode(email = email)
        }
    }

    override fun onCleared() {
        super.onCleared()
        timer?.cancel()
    }
}