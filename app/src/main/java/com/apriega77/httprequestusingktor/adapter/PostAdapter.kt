package com.apriega77.httprequestusingktor.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.apriega77.httprequestusingktor.R
import com.apriega77.httprequestusingktor.data.dto.PostResponse
import kotlinx.android.synthetic.main.rv_user.view.*


class PostAdapter : RecyclerView.Adapter<PostAdapter.PostViewHolder>() {
    var postList = mutableListOf<PostResponse>()

    class PostViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val tvName = view.tvName
        val tvEmail = view.tvEmail


        fun bind(data : PostResponse) {
            tvName.text = data.title
            tvEmail.text = data.body

        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PostAdapter.PostViewHolder {
        val inflater = LayoutInflater.from(parent.context).inflate(R.layout.rv_user, parent,false )

        return PostViewHolder(inflater)
    }

    override fun onBindViewHolder(holder: PostAdapter.PostViewHolder, position: Int) {
        holder.bind(postList[position])

    }

    override fun getItemCount(): Int {
      return  postList.size
    }


}