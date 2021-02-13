package com.example.firsttask.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query

@Dao
interface PostDao {

   @Insert
   fun insertPosts(posts:ArrayList<PostsData>)


    @Query("SELECT * FROM posts_table")
    fun getPosts():List<PostsData>

}