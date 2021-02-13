package com.example.firsttask.database

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "posts_table")
data class PostsData(

    @ColumnInfo(name = "id")
    var userId: Int,
    @PrimaryKey
    @ColumnInfo(name = "postId")
    var id: Int,
    @ColumnInfo(name = "postTitle")
    var title: String,
    @ColumnInfo(name = "postBody")
    var body: String

)