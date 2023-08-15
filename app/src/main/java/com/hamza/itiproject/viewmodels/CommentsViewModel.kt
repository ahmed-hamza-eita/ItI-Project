package com.hamza.itiproject.viewmodels

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.hamza.itiproject.models.ModelComments
import com.hamza.itiproject.repository.CommentsRepository
import kotlinx.coroutines.launch

class CommentsViewModel : ViewModel() {
    var commentsLiveData = MutableLiveData<ModelComments>()
    var errorLiveData = MutableLiveData<String>()
    private val repository: CommentsRepository = CommentsRepository()

    init {
        commentsLiveData = repository.commentsLiveData
        errorLiveData = repository.errorLiveData
    }

    fun getComments(commentId: Int) {
        viewModelScope.launch {
            repository.getComments(commentId)
        }
    }
}