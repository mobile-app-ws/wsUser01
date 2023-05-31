package com.dashkovskiy.world_skills_medic_app.modules

import com.dashkovskiy.world_skills_medic_app.ui.onboard.OnboardViewModel
import org.koin.androidx.viewmodel.dsl.viewModelOf
import org.koin.dsl.module

val viewModelsModule = module {
    viewModelOf(::OnboardViewModel)
}