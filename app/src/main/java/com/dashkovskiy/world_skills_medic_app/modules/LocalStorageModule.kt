package com.dashkovskiy.world_skills_medic_app.modules

import com.dashkovskiy.world_skills_medic_app.ui.utils.LocalStorage
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.module

val localStorageModule = module {
    singleOf(::LocalStorage)
}