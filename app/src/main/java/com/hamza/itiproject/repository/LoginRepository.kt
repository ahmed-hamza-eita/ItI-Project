package com.hamza.itiproject.repository

import androidx.lifecycle.MutableLiveData
import com.hamza.itiproject.models.ModelLogin
import com.hamza.itiproject.models.ModelUser
import com.hamza.itiproject.network.ApiCalls
import com.hamza.itiproject.network.RetrofitConnection
import com.hamza.itiproject.utils.Const
import org.json.JSONObject

class LoginRepository {
    val loginLiveData = MutableLiveData<ModelUser?>()
    var successLoginLiveData = MutableLiveData<ModelLogin>()
    var errorLiveData = MutableLiveData<String>()


    suspend fun login(email: String, password: String) {
        try {
            val result = RetrofitConnection.getRetrofit(Const.BASE_URL_LOGIN)
                .login(ModelLogin(email, password))
            if (result.isSuccessful)
                loginLiveData.postValue(result.body())
            else {
                val error = JSONObject(result.errorBody()?.string())
                errorLiveData.postValue(error.getString("message"))
            }
        } catch (e: Exception) {


            errorLiveData.postValue(e.localizedMessage)

        }
    }

}