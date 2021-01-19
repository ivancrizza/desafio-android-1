package com.picpay.desafio.android.di.koin.modules.repository

import com.picpay.desafio.android.PicPayRepository
import org.koin.dsl.module

val  picPayRepositoryModule = module {
    factory { PicPayRepository() }
}