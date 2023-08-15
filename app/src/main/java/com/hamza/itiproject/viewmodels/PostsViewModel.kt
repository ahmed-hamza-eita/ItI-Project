package com.hamza.itiproject.viewmodels

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.hamza.itiproject.models.ModelPosts
import com.hamza.itiproject.repository.LoginRepository
import com.hamza.itiproject.repository.PostsRepository
import kotlinx.coroutines.launch

class PostsViewModel : ViewModel() {
    var getPostsLiveData = MutableLiveData<ModelPosts?>()
    var errorLiveData = MutableLiveData<String>()
    private var repository: PostsRepository = PostsRepository()

    init {
        getPostsLiveData = repository.getPostsLiveData
        errorLiveData = repository.errorLiveData
    }


    fun getPosts(postId: String) {
        viewModelScope.launch {
            repository.getPosts(postId)
        }
    }
}