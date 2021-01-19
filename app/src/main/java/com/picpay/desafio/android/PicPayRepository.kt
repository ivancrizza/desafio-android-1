package com.picpay.desafio.android

import com.picpay.desafio.android.infrastructure.coroutines.AppContextProvider
import kotlinx.coroutines.withContext

class PicPayRepository {
    suspend fun getUsers() = withContext(AppContextProvider.io){
        return@withContext PicPayService.getPicPayAPI().getUsers()
    }

}