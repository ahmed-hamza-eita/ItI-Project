package com.hamza.itiproject.network

import com.hamza.itiproject.models.ModelComments
import com.hamza.itiproject.models.ModelLogin
import com.hamza.itiproject.models.ModelPosts
import com.hamza.itiproject.models.ModelUser
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path
import retrofit2.http.Query

interface ApiCalls {
    @GET("posts")
    suspend fun getPosts(@Query("userId") userId: String): Response<ModelPosts>

    @GET("posts/{post_id}/comments")
    suspend fun getComments(@Path("post_id") postId: Int):Response<ModelComments>

    @POST("auth/login")
    suspend fun login(@Body user: ModelLogin):Response<ModelUser>
}