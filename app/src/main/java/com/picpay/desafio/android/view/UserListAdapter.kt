package com.picpay.desafio.android.view

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.picpay.desafio.android.R
import com.picpay.desafio.android.model.User
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.list_item_user.view.*


class UserListAdapter
    (var users: List<User>) :
    RecyclerView.Adapter<UserListAdapter.ViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.list_item_user, parent, false)

        return ViewHolder(view)
    }


    override fun getItemCount(): Int = users.size


    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bind(user: User) {
            itemView.apply {
                name.text = user.name
                username.text = user.username
            }
            Picasso.get()
                .load(user.img)
                .error(R.drawable.ic_round_account_circle)
                .into(itemView.picture)
        }

    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) = holder.bind(users[position])

    fun updateUserList(users: MutableList<User>) {
        this.users = users
        notifyDataSetChanged()

    }
}