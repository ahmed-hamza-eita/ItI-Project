package com.hamza.itiproject.repository

import androidx.lifecycle.MutableLiveData
import com.hamza.itiproject.models.ModelComments
import com.hamza.itiproject.network.RetrofitConnection
import com.hamza.itiproject.utils.Const
import java.lang.Exception

class CommentsRepository {
    var commentsLiveData = MutableLiveData<ModelComments>()
    var errorLiveData = MutableLiveData<String>()

    suspend fun getComments(commentId: Int) {
        try {
            val result = RetrofitConnection.getRetrofit(Const.BASE_URL_POST).getComments(commentId)
            if (result.isSuccessful) {
                commentsLiveData.postValue(result.body())
            } else {
                errorLiveData.postValue(result.message())
            }
        } catch (e: Exception) {
            errorLiveData.postValue(e.localizedMessage)
        }
    }
}