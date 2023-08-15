package com.hamza.itiproject.adapters;

import android.annotation.SuppressLint
 import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
 import com.hamza.itiproject.models.ModelComments
import com.hamza.itiproject.databinding.ItemCommentBinding


class AdapterComments : RecyclerView.Adapter<AdapterComments.Holder>() {

    var list: ArrayList<ModelComments.ModelCommentsItem>? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Holder {
        val binding = ItemCommentBinding.inflate(
            LayoutInflater.from(parent.context),
            parent, false
        )
        return Holder(binding)
    }

    @SuppressLint("SetTextI18n")
    override fun onBindViewHolder(holder: Holder, position: Int) {
        val dataComment = list?.get(position)!!
        holder.binding.apply {

            txtPostId.text = "UserId : ${dataComment.postId.toString()}"
            txtId.text = "Id : ${dataComment.id.toString()}"
            txtName.text = "Title : ${dataComment.name}"
            txtEmail.text = "E-mail : ${dataComment.email}"
            txtBody.text = "Comment : ${dataComment.body}"


        }
    }

    override fun getItemCount(): Int {
        return list?.size ?: 0
    }

    inner class Holder constructor(val binding: ItemCommentBinding) :
        RecyclerView.ViewHolder(binding.root) {

    }


}