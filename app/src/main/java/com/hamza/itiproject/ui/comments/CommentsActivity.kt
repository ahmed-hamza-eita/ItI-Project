package com.hamza.itiproject.ui.comments

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import com.hamza.itiproject.adapters.AdapterComments
import com.hamza.itiproject.network.RetrofitConnection
import com.hamza.itiproject.utils.ProgressLoading
import com.hamza.itiproject.databinding.ActivityCommentsBinding
import com.hamza.itiproject.utils.Const
import com.hamza.itiproject.utils.showToast
import com.hamza.itiproject.viewmodels.CommentsViewModel
import kotlinx.coroutines.launch

class CommentsActivity : AppCompatActivity() {
    private var _binding: ActivityCommentsBinding? = null
    private val binding get() = _binding!!
    private val adapter = AdapterComments()
    private lateinit var commentsViewModel: CommentsViewModel


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = ActivityCommentsBinding.inflate(layoutInflater)
        setContentView(_binding?.root)
        commentsViewModel = ViewModelProvider(this)[CommentsViewModel::class.java]
        val commentId = intent.getIntExtra(Const.POST_ID_KEY, 0)
        //getComments(commentId)
        commentsViewModel.getComments(commentId)

        observer()
    }

    private fun observer() {
        ProgressLoading.show(this)
        commentsViewModel.apply {
            commentsLiveData.observe(this@CommentsActivity) {
                adapter.list = it
                binding.rv.adapter = adapter
                ProgressLoading.dismiss()
            }
            errorLiveData.observe(this@CommentsActivity) {
                ProgressLoading.dismiss()
                showToast(it)
            }
        }
    }

    private fun getComments(commentId: Int) {

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