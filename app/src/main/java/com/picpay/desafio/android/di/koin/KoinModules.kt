package com.picpay.desafio.android.di.koin

import android.content.Context
import com.picpay.desafio.android.PicPayRepository
import com.picpay.desafio.android.viewmodel.UserListViewModel
import kotlinx.coroutines.Dispatchers
import org.koin.android.ext.koin.androidContext
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.core.context.startKoin
import org.koin.dsl.module

fun startKoin(context: Context) {
    startKoin {
        androidContext(context)
        modules(viewModelModules + repositoryModules)
    }
}
val picPayRepositoryModule = module {
    factory { PicPayRepository() }
}

val userListModule = module {
    viewModel { UserListViewModel(Dispatchers.IO, get()) }
}

val viewModelModules = listOf(
    userListModule
)

val repositoryModules = listOf(
    picPayRepositoryModule
)
