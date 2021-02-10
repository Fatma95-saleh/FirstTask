package com.example.firsttask.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.firsttask.MvvMApp
import com.example.firsttask.PostsAdapter.PostData

@Database(entities=[PostData::class],version=1)
abstract class Roomdb :RoomDatabase(){

    abstract fun userDao():PostDao

    companion object{

        private var INSTANCE: Roomdb?= null

        fun getAppDatabase(context: Context): Roomdb? {

            if(INSTANCE == null ) {

                INSTANCE = Room.databaseBuilder<Roomdb>(
                        MvvMApp.applicationContext(), Roomdb::class.java, "AppDBB"
                )
                        .build()

            }
            return INSTANCE
        }

    }

}