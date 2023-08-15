package com.hamza.itiproject.ui.comments

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.lifecycle.lifecycleScope
import com.hamza.itiproject.adapters.AdapterComments
import com.hamza.itiproject.network.RetrofitConnection
import com.hamza.itiproject.utils.ProgressLoading
import com.hamza.itiproject.databinding.ActivityCommentsBinding
import com.hamza.itiproject.utils.Const
import kotlinx.coroutines.launch

class CommentsActivity : AppCompatActivity() {
    private var _binding: ActivityCommentsBinding? = null
    private val binding get() = _binding!!
    private val adapter = AdapterComments()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = ActivityCommentsBinding.inflate(layoutInflater)
        setContentView(_binding?.root)

        getComments()
    }

    private fun getComments() {
        val commentId = intent.getIntExtra(Const.POST_ID_KEY, 0)
        lifecycleScope.launch {
            ProgressLoading.show(this@CommentsActivity)
            val result = RetrofitConnection.getRetrofit(Const.BASE_URL_POST).getComments(commentId)
            if (result.isSuccessful) {
                val comment = result.body()
                adapter.list = comment
                binding.rv.adapter = adapter
                ProgressLoading.dismiss()
            } else {
                Toast.makeText(this@CommentsActivity, "ERROR", Toast.LENGTH_LONG)
                ProgressLoading.dismiss()
            }
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}