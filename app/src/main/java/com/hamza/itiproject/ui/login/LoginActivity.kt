package com.hamza.itiproject.ui.login

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import com.hamza.itiproject.models.ModelLogin
import com.hamza.itiproject.network.RetrofitConnection
import com.hamza.itiproject.utils.MySharedPreferences
import com.hamza.itiproject.utils.ProgressLoading
import com.hamza.itiproject.R
import com.hamza.itiproject.databinding.ActivityLoginBinding
import com.hamza.itiproject.ui.posts.MainActivity
import com.hamza.itiproject.utils.Const
import com.hamza.itiproject.viewmodels.LoginViewModel
import kotlinx.coroutines.launch
import org.json.JSONObject

class LoginActivity : AppCompatActivity() {
    private var _binding: ActivityLoginBinding? = null
    private val binding get() = _binding!!
    private lateinit var loginViewModel: LoginViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(_binding?.root)
        loginViewModel = ViewModelProvider(this)[LoginViewModel::class.java]
        actions()
         observers()

    }

    private fun observers() {

        loginViewModel.apply {
             loginLiveData.observe(this@LoginActivity) {

                intent()
                ProgressLoading.dismiss()
            }
            errorLiveData.observe(this@LoginActivity) {
                Toast.makeText(this@LoginActivity, it, Toast.LENGTH_SHORT)
                    .show()
                ProgressLoading.dismiss()
            }
        }
    }

    private fun actions() {
        binding.btnLogin.setOnClickListener {
             checkLogin()

        }
    }

    private fun checkLogin() {
        val email = binding.edtEmail.text.trim().toString()
        val password = binding.edtPassword.text.trim().toString()
        if (email.isEmpty()) {
            binding.edtEmail.error = getString(R.string.required)
        } else if (password.isEmpty()) {
            binding.edtPassword.error = getString(R.string.required)
        } else {
            ProgressLoading.show(this)
            loginViewModel.login(email, password)
            MySharedPreferences.setUserEmail(email)
            MySharedPreferences.getBoolean(Const.LOGEDIN_KEY, true)
        }
    }



    private fun intent() {
        val intent = Intent(this, MainActivity::class.java)
        startActivity(intent)
        finish()
    }
/*
    private fun login(email: String, password: String) {
        lifecycleScope.launch {
            val result = RetrofitConnection.getRetrofit(Const.BASE_URL_LOGIN)
                .login(ModelLogin(email, password))

            if (result.isSuccessful) {


                intent()
                ProgressLoading.dismiss()

            } else {
                val error = JSONObject(result.errorBody()?.string())
                Toast.makeText(this@LoginActivity, error.getString("message"), Toast.LENGTH_SHORT)
                    .show()
                ProgressLoading.dismiss()
            }
        }
    }

 */
    override fun onDestroy() {
        super.onDestroy()
        _binding
    }
}