package com.hamza.itiproject.repository

import androidx.lifecycle.MutableLiveData
import com.hamza.itiproject.models.ModelPosts
import com.hamza.itiproject.network.RetrofitConnection
import com.hamza.itiproject.utils.Const

class PostsRepository {

    var getPostsLiveData = MutableLiveData<ModelPosts?>()
    var errorLiveData = MutableLiveData<String>()

    suspend fun getPosts(postId: String) {
        try {
            val result = RetrofitConnection.getRetrofit(Const.BASE_URL_POST).getPosts(postId)
            if (result.isSuccessful) {
                getPostsLiveData.postValue(result.body())
            }
        } catch (e: Exception) {
            errorLiveData.postValue(e.message)
        }
    }

}

