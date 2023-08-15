package com.hamza.itiproject.viewmodels

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.hamza.itiproject.models.ModelLogin
import com.hamza.itiproject.models.ModelUser
import com.hamza.itiproject.repository.LoginRepository
import kotlinx.coroutines.launch

class LoginViewModel : ViewModel() {


    var loginLiveData = MutableLiveData<ModelUser?>()
    var successLoginLiveData = MutableLiveData<ModelLogin>()
    var errorLiveData = MutableLiveData<String>()

    private var repository: LoginRepository = LoginRepository()


    init {
        loginLiveData = repository.loginLiveData
        successLoginLiveData = repository.successLoginLiveData
        errorLiveData = repository.errorLiveData
    }

    fun login(email: String, password: String) {
        viewModelScope.launch {
            repository.login(email, password)
        }
    }
}