package com.dashkovskiy.world_skills_medic_app.ui.patientmap

import androidx.lifecycle.ViewModel
import com.dashkovskiy.world_skills_medic_app.api.Gender
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow

data class PatientMapState(
    val name: String = "",
    val surname: String = "",
    val patronymic: String = "",
    val birthday: String = "",
    val gender: Gender = Gender.NOT_SPECIFIED
) {
    val btnEnabled : Boolean
        get() =
            name.isNotEmpty() && surname.isNotEmpty() && patronymic.isNotEmpty() && birthday.isNotEmpty() && gender != Gender.NOT_SPECIFIED
}

class PatientMapViewModel : ViewModel() {

    private val _state = MutableStateFlow(PatientMapState())
    val viewState = _state.asStateFlow()

    private val state: PatientMapState
        get() = _state.value

    fun setName(name: String) {
        _state.value = state.copy(name = name)
    }

    fun setSurname(surname: String) {
        _state.value = state.copy(surname = surname)
    }

    fun setPatronymic(patronymic: String) {
        _state.value = state.copy(patronymic = patronymic)
    }

    fun setBirthday(birthday : String) {
        //todo : what type in api?
    }

    fun setGender(gender: Gender) {
        _state.value = state.copy(gender = gender)
    }
}