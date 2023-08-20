package com.hotmail.or_dvir.televizia

import android.app.Application
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin


class TeleviziaApplication : Application() {
    override fun onCreate() {
        super.onCreate()

        startKoin {
            androidLogger()
            androidContext(this@TeleviziaApplication)
            stopped here
            modules(modules)
        }
    }
}