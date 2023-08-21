package com.hotmail.or_dvir.televizia

import android.app.Application
import com.hotmail.or_dvir.televizia.di.repositoriesModule
import com.hotmail.or_dvir.televizia.di.viewModelsModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin

class TeleviziaApplication : Application() {
    override fun onCreate() {
        super.onCreate()

        startKoin {
            androidLogger()
            androidContext(this@TeleviziaApplication)
            modules(repositoriesModule, viewModelsModule)
        }
    }
}