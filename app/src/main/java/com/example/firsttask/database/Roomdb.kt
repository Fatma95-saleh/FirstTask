package com.example.firsttask.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.firsttask.MvvMApp
import com.example.firsttask.PostsAdapter.PostData

@Database(entities = [PostsData::class], version = 2, exportSchema = false)
abstract class Roomdb : RoomDatabase() {

    abstract val postsDao: PostDao

    companion object {

        @Volatile
        private var INSTANCE: Roomdb? = null

        fun getInstance(context: Context): Roomdb {
            synchronized(this) {
                var instance = INSTANCE

                if (instance == null) {
                    instance = Room.databaseBuilder(
                        context.applicationContext,
                        Roomdb::class.java,
                        "posts_db"
                    )
                        .fallbackToDestructiveMigration()
                        .build()
                    INSTANCE = instance
                }
                return instance
            }
        }
    }
}
