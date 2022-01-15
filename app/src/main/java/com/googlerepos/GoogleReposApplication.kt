package com.googlerepos

import android.app.Application
import com.googlerepos.di.*
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin

class GoogleReposApplication : Application() {

    override fun onCreate() {
        super.onCreate()

        startKoin {
            androidLogger()
            androidContext(this@GoogleReposApplication)
            modules(
                listOf(
                    networkModule,
                    repositoryModule,
                    fragmentModule,
                    viewModelModule,
                    reposModule
                )
            )
        }
    }
}