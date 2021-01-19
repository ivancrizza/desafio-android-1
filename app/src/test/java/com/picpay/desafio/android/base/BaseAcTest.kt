package com.picpay.desafio.android.base

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.picpay.desafio.android.coroutines.ContextProviderTest
import com.picpay.desafio.android.infrastructure.coroutines.AppContextProvider
import io.mockk.MockKAnnotations
import org.junit.Before
import org.junit.Rule
import org.junit.runner.RunWith
import org.junit.runners.JUnit4

@RunWith(JUnit4::class)
abstract class BaseAcTest {

    @get:Rule
    val rule = InstantTaskExecutorRule()

    @Before
    open fun baseSetUp(){
        MockKAnnotations.init(this,relaxUnitFun = true)
        AppContextProvider.coroutinesContextProviderDelegate = ContextProviderTest()
    }
}