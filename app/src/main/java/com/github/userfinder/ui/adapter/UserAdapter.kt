package com.github.userfinder.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.github.userfinder.R
import com.github.userfinder.data.model.User
import com.github.userfinder.databinding.ViewHolderUserBinding

class UserAdapter : RecyclerView.Adapter<UserAdapter.MyViewHolder>(){


    private var list : MutableList<User> = arrayListOf()
    fun setData(data : List<User>?){
        list.clear()
        data?.let {
            list.addAll(data)
        }
        notifyDataSetChanged()
    }

    fun addMoreUser(data : List<User>?){
        val positionStart = list.size
        data?.let {
            list.addAll(data)
            notifyItemRangeInserted(positionStart, data.size)
        }
    }

    fun getList() : List<User> = list

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UserAdapter.MyViewHolder {
        return MyViewHolder(
            DataBindingUtil.inflate(
                LayoutInflater.from(parent.context), R.layout.view_holder_user, parent,
                false
            )
        )
    }

    override fun getItemCount(): Int {
        return list.size
    }

    override fun onBindViewHolder(holder: UserAdapter.MyViewHolder, position: Int) {
       holder.bind(list[position])
    }

    inner class MyViewHolder(private val binding: ViewHolderUserBinding) : RecyclerView.ViewHolder(binding.root) {

        fun bind(user : User){
            binding.tvNameName.text = user.login
            Glide.with(itemView.context).load(user.avatarUrl).into(binding.ivUserAvatar)
        }
    }

}