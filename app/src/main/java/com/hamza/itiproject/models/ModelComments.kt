package com.hamza.itiproject.models

class ModelComments : ArrayList<ModelComments.ModelCommentsItem>(){
    data class ModelCommentsItem(
        val body: String,
        val email: String,
        val id: Int,
        val name: String,
        val postId: Int
    )
}