package com.hariagus.staterproject

import android.app.Application
import com.hariagus.staterproject.core.di.databaseModule
import com.hariagus.staterproject.core.di.networkModule
import com.hariagus.staterproject.core.di.repositoryModule
import com.hariagus.staterproject.di.useCaseModule
import com.hariagus.staterproject.di.viewModelModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin
import org.koin.core.logger.Level

class MyApplication : Application() {
    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidLogger(Level.NONE)
            androidContext(this@MyApplication)
            modules(
                listOf(
                    databaseModule,
                    networkModule,
                    repositoryModule,
                    useCaseModule,
                    viewModelModule
                )
            )
        }
    }
}