package com.picpay.desafio.android.di.koin.modules

import com.picpay.desafio.android.viewmodel.UserListViewModel
import kotlinx.coroutines.ExperimentalCoroutinesApi
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module


val userListModule = module {
    viewModel { UserListViewModel(get()) }
}