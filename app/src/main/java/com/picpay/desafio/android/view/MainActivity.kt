package com.picpay.desafio.android.view

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
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

        viewModel.userlisState.observe(this, Observer<UserListViewModel.UserListState> {
            when (it) {
                is UserListViewModel.UserListState.Success -> {
                    updateListOfUsers(it.users as MutableList<User>)
                }
                is UserListViewModel.UserListState.Error -> {
                    errorMessage(R.string.error.toString())
                }
                is UserListViewModel.UserListState.Loading -> {
                    loadinState(it.loading)

                }
               else -> {
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

    private fun loadinState(show: Boolean) {
        when {
            show -> {
                user_list_progress_bar.visibility = View.VISIBLE
            }
            else -> {
                user_list_progress_bar.visibility = View.GONE
            }

        }
    }

}
