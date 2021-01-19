package com.picpay.desafio.android.infrastructure

import androidx.lifecycle.ViewModel
import com.picpay.desafio.android.infrastructure.coroutines.AppContextProvider
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Job
import kotlin.coroutines.CoroutineContext

abstract class BaseAcViewModel : ViewModel(), CoroutineScope {
    private val job = Job()
    override val coroutineContext: CoroutineContext by lazy { job + AppContextProvider.io }
    override fun onCleared() {
        job.cancel()
        super.onCleared()
    }

}