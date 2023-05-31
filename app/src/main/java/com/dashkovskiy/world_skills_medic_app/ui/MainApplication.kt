package com.dashkovskiy.world_skills_medic_app.ui

import android.app.Application
import com.dashkovskiy.world_skills_medic_app.modules.localStorageModule
import com.dashkovskiy.world_skills_medic_app.modules.retrofitModule
import com.dashkovskiy.world_skills_medic_app.modules.viewModelsModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin

class MainApplication : Application() {
    override fun onCreate() {
        super.onCreate()
        startKoin{
            androidLogger()
            androidContext(this@MainApplication)
            modules(retrofitModule, viewModelsModule, localStorageModule)
        }
    }
}