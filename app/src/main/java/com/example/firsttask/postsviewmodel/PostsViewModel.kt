package com.example.firsttask.postsviewmodel

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.firsttask.MvvMApp
import com.example.firsttask.database.PostsData
import com.example.firsttask.database.Roomdb
import com.example.firsttask.network.NetworkManager
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import retrofit2.awaitResponse

class PostsViewModel() :ViewModel() {

     var postsData:MutableLiveData<ArrayList<PostsData>>
     var localPosts:MutableLiveData<ArrayList<PostsData>>
    init {
        postsData= MutableLiveData()
        localPosts= MutableLiveData()
    }

    fun getPostsObserver(): MutableLiveData<ArrayList<PostsData>> {

        return postsData

    }
    fun getPostsLocal(): MutableLiveData<ArrayList<PostsData>> {

        return localPosts

    }


    fun getPosts(){

      /*  NetworkManager.retrofitConnection().getData().enqueue(object:Callback<ArrayList<PostsData>>{
            override fun onFailure(call: Call<ArrayList<PostsData>>, t: Throwable) {
                Log.e("connection","failed")
            }

            override fun onResponse(call: Call<ArrayList<PostsData>>, response: Response<ArrayList<PostsData>>) {

                if(response.isSuccessful){
                    Log.e("connection","success")

                    postsData.postValue(response.body())

                }
            }
        })*/


        GlobalScope.launch(Dispatchers.IO) {
            delay(3000)

            var response=NetworkManager.retrofitConnection().getData().awaitResponse()
            if(response.isSuccessful){
                postsData.postValue(response.body())

                //local posts
                try {
                    Roomdb.getAppDatabase(MvvMApp.applicationContext())?.userDao()?.insertPosts(response.body()!!)
                    localPosts.postValue(response.body())

                    localPosts.postValue(Roomdb.getAppDatabase(MvvMApp.applicationContext())?.userDao()?.getPosts())

                }catch (e:Exception){
                    Log.i("ConnectionException" , "${e.message}")
                }

            }
        }
    }
}