package com.picpay.desafio.android.coroutines

import com.picpay.desafio.android.infrastructure.coroutines.CoroutinesContextProvider
import kotlinx.coroutines.Dispatchers
import kotlin.coroutines.CoroutineContext

class ContextProviderTest : CoroutinesContextProvider {
    override val main: CoroutineContext = Dispatchers.Unconfined
    override val io: CoroutineContext = Dispatchers.Unconfined
}
