package com.picpay.desafio.android.viewmodel

import com.picpay.desafio.android.PicPayRepository
import com.picpay.desafio.android.base.BaseAcTest
import com.picpay.desafio.android.model.User
import io.mockk.coEvery
import io.mockk.every
import io.mockk.impl.annotations.MockK
import io.mockk.mockk
import kotlinx.coroutines.runBlocking
import org.junit.Assert
import org.junit.Before
import org.junit.Test

class UserListViewModelTest : BaseAcTest() {

    @MockK
    lateinit var repository: PicPayRepository

    @MockK
    lateinit var viewModel: UserListViewModel


    val users = mockk<User> {
        every { name } returns "Aldo"
        every { id } returns 2
        every { img } returns "imageTest"
        every { username } returns "Aldo.Alvez"

    }

    @Before
    fun setUp() {


    }

    override fun baseSetUp() {
        super.baseSetUp()
        viewModel = UserListViewModel(
            repository
        )
    }

    @Test
    fun getAllContactsList() {
        runBlocking {
            viewModel.getUsers()
        }

        coEvery { repository.getUsers() } returns (listOf(users))

        Assert.assertEquals(UserListViewModel.UserListState.Success(listOf(users)),viewModel.userlisState.value)

    }
}