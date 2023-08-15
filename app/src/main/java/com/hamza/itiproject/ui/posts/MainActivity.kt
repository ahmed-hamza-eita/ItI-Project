package com.hamza.itiproject.ui.posts

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import com.hamza.itiproject.adapters.AdapterPosts
import com.hamza.itiproject.network.RetrofitConnection
import com.hamza.itiproject.utils.ProgressLoading
import com.hamza.itiproject.databinding.ActivityMainBinding
import com.hamza.itiproject.utils.Const
import com.hamza.itiproject.utils.showToast
import com.hamza.itiproject.viewmodels.PostsViewModel
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {
    private var _binding: ActivityMainBinding? = null
    private val binding get() = _binding!!
    private val adapter = AdapterPosts()
    private lateinit var postsViewModel: PostsViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(_binding?.root)
        postsViewModel = ViewModelProvider(this)[PostsViewModel::class.java]


        actions()
        //observer()
    }

    private fun observer() {
        ProgressLoading.show(this)
        postsViewModel.apply {
            getPostsLiveData.observe(this@MainActivity) {
                adapter.list = it
                binding.rv.adapter = adapter
                ProgressLoading.dismiss()
            }
            errorLiveData.observe(this@MainActivity) {
                showToast(it)
                ProgressLoading.dismiss()

            }
        }
    }

    private fun actions() {
        binding.btnGetPosts.setOnClickListener {
            //getPosts()
            postsViewModel.getPosts(binding.edtInputId.text.toString())
            observer()
        }
    }

    /*
        private fun getPosts() {
            ProgressLoading.show(this)
            lifecycleScope.launch {
                val result =
                    RetrofitConnection.getRetrofit(Const.BASE_URL_POST)
                        .getPosts(binding.edtInputId.text.toString())
                if (result.isSuccessful) {
                    val posts = result.body()
                    adapter.list = posts
                    binding.rv.adapter = adapter
                    ProgressLoading.dismiss()
                } else {
                    Toast.makeText(this@MainActivity, "ERROR", Toast.LENGTH_LONG)
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