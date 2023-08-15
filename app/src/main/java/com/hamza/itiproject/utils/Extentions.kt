package com.hamza.itiproject.utils;

import android.app.Activity
import android.app.Application
import android.view.View
import androidx.fragment.app.Fragment
 import com.hamza.itiproject.R
import io.github.muddz.styleabletoast.StyleableToast

fun Activity.showToast(message: Any?) {
    StyleableToast.makeText(this, "$message", R.style.toastStyle).show()
}


