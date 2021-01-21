package com.picpay.desafio.android

import com.picpay.desafio.android.model.User
import retrofit2.http.GET

interface PicPayAPI {
    @GET("users")
    suspend fun getUsers(): List<User>

    companion object {
        const val USERS = "users"
    }
}