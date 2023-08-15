package com.hamza.itiproject.adapters;

import android.annotation.SuppressLint
import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.hamza.itiproject.models.ModelPosts
import com.hamza.itiproject.ui.comments.CommentsActivity
import com.hamza.itiproject.databinding.ItemPostBinding
import com.hamza.itiproject.utils.Const


class AdapterPosts : RecyclerView.Adapter<AdapterPosts.Holder>() {

    var list: ArrayList<ModelPosts.ModelPostsItem>? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Holder {
        val binding = ItemPostBinding.inflate(
            LayoutInflater.from(parent.context),
            parent, false
        )
        return Holder(binding)
    }

    @SuppressLint("SetTextI18n")
    override fun onBindViewHolder(holder: Holder, position: Int) {
        val dataPost = list?.get(position)!!
        holder.binding.apply {
            txtUserId.text = "UserId : ${dataPost.userId.toString()}"
            txtId.text = "Id : ${dataPost.id.toString()}"
            txtTitle.text = "Title : ${dataPost.title}"
            txtBody.text = "Post : ${dataPost.body}"
            btnDetails.setOnClickListener {
                val intent = Intent(holder.itemView.context, CommentsActivity::class.java)
                intent.putExtra(Const.POST_ID_KEY, dataPost.id )
                holder.itemView.context.startActivity(intent)
            }
        }
    }

    override fun getItemCount(): Int {
        return list?.size ?: 0
    }

    inner class Holder constructor(val binding: ItemPostBinding) :
        RecyclerView.ViewHolder(binding.root) {

    }


}