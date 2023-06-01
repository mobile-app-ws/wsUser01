package com.dashkovskiy.world_skills_medic_app.modules

import com.dashkovskiy.world_skills_medic_app.ui.analyzes.AnalyzesViewModel
import com.dashkovskiy.world_skills_medic_app.ui.code.CodeInputViewModel
import com.dashkovskiy.world_skills_medic_app.ui.email.EmailInputViewModel
import com.dashkovskiy.world_skills_medic_app.ui.onboard.OnboardViewModel
import com.dashkovskiy.world_skills_medic_app.ui.patientmap.PatientMapViewModel
import org.koin.androidx.viewmodel.dsl.viewModelOf
import org.koin.dsl.module

val viewModelsModule = module {
    viewModelOf(::OnboardViewModel)
    viewModelOf(::EmailInputViewModel)
    viewModelOf(::CodeInputViewModel)
    viewModelOf(::PatientMapViewModel)
    viewModelOf(::AnalyzesViewModel)
}