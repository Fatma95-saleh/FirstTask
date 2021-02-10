package com.example.firsttask.network

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class NetworkManager {
    companion object{

        val baseUrl="https://jsonplaceholder.typicode.com/"

        fun retrofitConnection():Apis{

            var retrofit= Retrofit.Builder()
                .baseUrl(baseUrl)
                .addConverterFactory(GsonConverterFactory.create())
                .build()

            val service=retrofit.create(Apis::class.java)
            // val call=service.getData()
            return service

        }

    }

}