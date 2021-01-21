package com.picpay.desafio.android.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.picpay.desafio.android.PicPayRepository
import com.picpay.desafio.android.model.User
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class UserListViewModel(
    private val dispatcher: CoroutineDispatcher,
    private val repository: PicPayRepository
) : ViewModel() {

    private var _userListState = MutableLiveData<UserListState>()
    val userList = _userListState


    sealed class UserListState {
        data class Success(val users: List<User>) : UserListState()
        data class Error(val message: String) : UserListState()
        data class Loading(val loading: Boolean) : UserListState()
    }

    fun getUsers() {
        viewModelScope.launch {
            _userListState.value = (UserListState.Loading(true))
            try {
                val result = withContext(dispatcher) {
                    repository.getUsers()
                }
                _userListState.value = (UserListState.Success(result))

            } catch (error: Throwable) {
                _userListState.value = (UserListState.Error(error.message.toString()))
            }
        }
    }
}
