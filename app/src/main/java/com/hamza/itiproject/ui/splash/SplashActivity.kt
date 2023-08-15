package com.hamza.itiproject.ui.splash

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import com.hamza.itiproject.R
import com.hamza.itiproject.ui.login.LoginActivity
import com.hamza.itiproject.ui.posts.MainActivity
import com.hamza.itiproject.utils.MySharedPreferences

class SplashActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Thread.sleep(1000)
        installSplashScreen()
        checkIfUserLoggedIn()
        setContentView(R.layout.activity_splash)
    }

    private fun checkIfUserLoggedIn() {
        if (MySharedPreferences.getUserEmail().isEmpty()) {
            startActivity(Intent(this, LoginActivity::class.java))
        } else {
            startActivity(Intent(this, MainActivity::class.java))

        }
    }
}