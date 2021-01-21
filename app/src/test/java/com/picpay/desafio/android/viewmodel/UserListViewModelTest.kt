package com.picpay.desafio.android.viewmodel

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.picpay.desafio.android.PicPayRepository
import com.picpay.desafio.android.model.User
import io.mockk.MockKAnnotations
import io.mockk.coEvery
import io.mockk.impl.annotations.MockK
import io.mockk.mockk
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.test.TestCoroutineDispatcher
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.setMain
import org.junit.*

class UserListViewModelTest  {

    @get:Rule
    val instantExecutorRule = InstantTaskExecutorRule()

    @ExperimentalCoroutinesApi
    private val testCoroutineDispatcher = TestCoroutineDispatcher()

    @MockK
    lateinit var repository: PicPayRepository

    lateinit var viewModel: UserListViewModel

    val users = mockk<List<User>>()

    @ExperimentalCoroutinesApi
    @Before
    fun setup() {
        Dispatchers.setMain(Dispatchers.Unconfined)
        MockKAnnotations.init(this, relaxUnitFun = true)
        viewModel = UserListViewModel(testCoroutineDispatcher, repository)
    }

    @ExperimentalCoroutinesApi
    @After
    fun tearDown() {
        testCoroutineDispatcher.cleanupTestCoroutines()
        Dispatchers.resetMain()
    }

    @Test
    fun getAllContactsList() {
        coEvery { repository.getUsers() } returns (users)
        runBlocking {
            viewModel.getUsers()
        }
        Assert.assertEquals(
            UserListViewModel.UserListState.Success(users),
            viewModel.userList.value
        )
    }
}