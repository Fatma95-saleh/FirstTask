package com.example.firsttask.network

import com.example.firsttask.commentsadapter.CommentsData
import com.example.firsttask.database.PostsData
import retrofit2.Call
import retrofit2.http.GET

interface Apis {

    @GET("posts")
    fun getData():Call<ArrayList<PostsData>>

    @GET("comments")
    fun getComments():Call<ArrayList<CommentsData>>

}