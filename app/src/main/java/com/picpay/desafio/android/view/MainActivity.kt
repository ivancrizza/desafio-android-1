package com.picpay.desafio.android.view

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isVisible
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.material.snackbar.Snackbar
import com.picpay.desafio.android.R
import com.picpay.desafio.android.model.User
import com.picpay.desafio.android.viewmodel.UserListViewModel
import kotlinx.android.synthetic.main.activity_main.*
import org.koin.androidx.viewmodel.ext.android.viewModel


class MainActivity : AppCompatActivity() {
    private val adapter: UserListAdapter by lazy {
        UserListAdapter(
            ArrayList()
        )
    }

    private val viewModel: UserListViewModel by viewModel()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        recyclerView.adapter = adapter
        recyclerView.layoutManager = LinearLayoutManager(this)
        viewModel.getUsers()
        initObserver()

    }

    private fun initObserver() {

        viewModel.userList.observe(this, Observer<UserListViewModel.UserListState> {
            when (it) {
                is UserListViewModel.UserListState.Success -> {
                    updateListOfUsers(it.users.toMutableList() )
                }
                is UserListViewModel.UserListState.Error -> {
                    errorMessage(R.string.error.toString())
                }
                is UserListViewModel.UserListState.Loading -> {
                    loadingState(it.loading)
                }
            }
        })
    }

    private fun updateListOfUsers(listOfUsers: MutableList<User>) {
        adapter.updateUserList(listOfUsers)
    }

    private fun errorMessage(error: String) {
        Snackbar.make(recyclerView, error, Snackbar.LENGTH_SHORT).show()
    }

    private fun loadingState(show: Boolean) {
        user_list_progress_bar.isVisible = show
    }

}
