package com.example.firsttask.database

import androidx.room.Room
import com.example.firsttask.MvvMApp


//object DataBaseBuilder{
//
//
//    @Volatile
//    private var instance:DataBase?=null
//
//    fun getDataBase():DataBase?{
//
//        if (instance==null){
//
//            synchronized(DataBase::class.java){
//                instance=Room.databaseBuilder(
//                    MvvMApp.applicationContext(),
//                    DataBase::class.java,"postdatabase")
//                   .build()
//            }
//        }
//
//        return instance
//    }
//}