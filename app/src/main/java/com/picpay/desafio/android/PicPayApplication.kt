package com.picpay.desafio.android

import android.app.Application
import com.picpay.desafio.android.di.koin.repositoryModules
import com.picpay.desafio.android.di.koin.viewModelModules
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin

open class PicPayApplication : Application() {

    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidLogger()
            androidContext(this@PicPayApplication)
            modules(viewModelModules + repositoryModules)
        }

    }
}
