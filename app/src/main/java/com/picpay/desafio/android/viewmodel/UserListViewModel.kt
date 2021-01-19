package com.picpay.desafio.android.viewmodel

import androidx.lifecycle.MutableLiveData
import com.picpay.desafio.android.PicPayRepository
import com.picpay.desafio.android.R
import com.picpay.desafio.android.infrastructure.BaseAcViewModel
import com.picpay.desafio.android.infrastructure.coroutines.AppContextProvider
import com.picpay.desafio.android.model.User
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext


class UserListViewModel(private val repository: PicPayRepository) : BaseAcViewModel() {

    var userlisState = MutableLiveData<UserListState>()

    sealed class UserListState {
        data class Success(val users: List<User>) : UserListState()
        data class Error(val message: String) : UserListState()
        data class Loading(val loading: Boolean) : UserListState()
    }

    fun getUsers() {
        launch {
            userlisState.postValue(UserListState.Loading(true))
            try {
                val result = repository.getUsers()
                onSuccessList(result)
                userlisState.postValue(UserListState.Loading(false))
            } catch (error: Throwable) {
                onErrorList(R.string.error.toString())

            }
        }

    }

    private suspend fun onSuccessList(users: List<User>) {
        withContext(AppContextProvider.main) {
            userlisState.postValue(UserListState.Success(users))
        }
    }

    private suspend fun onErrorList(message: String) {
        withContext(AppContextProvider.main) {
            userlisState.postValue(UserListState.Error(message))
        }
    }


}