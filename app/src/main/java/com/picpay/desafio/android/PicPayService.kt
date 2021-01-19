package com.picpay.desafio.android

import com.google.gson.GsonBuilder
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


class PicPayService {

    companion object {
        private val httpClient = OkHttpClient.Builder()
        private var url = "http://careers.picpay.com/tests/mobdev/"
        private val gson = GsonBuilder().setLenient().create()


        fun getPicPayAPI(): PicPayAPI {
            return Retrofit.Builder()
                .baseUrl(url)
                .client(httpClient.build())
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build()
                .create(PicPayAPI::class.java)

        }

        init {
            val client = OkHttpClient.Builder()
            val interceptor = HttpLoggingInterceptor()
            interceptor.level = HttpLoggingInterceptor.Level.BODY
            client.addInterceptor(interceptor)
        }

    }
}

