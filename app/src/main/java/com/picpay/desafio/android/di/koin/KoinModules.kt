package com.picpay.desafio.android.di.koin

import android.content.Context
import com.picpay.desafio.android.di.koin.modules.repository.picPayRepositoryModule
import com.picpay.desafio.android.di.koin.modules.userListModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

fun startKoin(context: Context) {
    startKoin {
        androidContext(context)
        modules(viewModelModules + repositoryModules)
    }
}

val viewModelModules = listOf(
    userListModule
)
val repositoryModules = listOf(
    picPayRepositoryModule
)