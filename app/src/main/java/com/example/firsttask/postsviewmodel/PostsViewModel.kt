package com.example.firsttask.postsviewmodel

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.firsttask.database.PostsData
import com.example.firsttask.network.NetworkManager
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class PostsViewModel() :ViewModel() {

     var postsData:MutableLiveData<ArrayList<PostsData>>
     var localPosts:MutableLiveData<ArrayList<PostsData>>
     var error_msg:MutableLiveData<String> = MutableLiveData()
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
        GlobalScope.launch(Dispatchers.IO) {
            NetworkManager.retrofitConnection().getData().enqueue(object :Callback<ArrayList<PostsData>>{
                override fun onResponse(
                    call: Call<ArrayList<PostsData>>,
                    response: Response<ArrayList<PostsData>>
                ) {
                    if(response.isSuccessful){
                        try {
                            postsData.postValue(response.body())
                        }catch (e:Exception){
                            error_msg.postValue(e.message)
                            Log.i("ConnectionException" , "${e.message}")
                        }

                    }else{
                        error_msg.postValue(response.errorBody()?.string())

                    }

                }

                override fun onFailure(call: Call<ArrayList<PostsData>>, t: Throwable) {
                    Log.i("ConnectionException" , "${t.message}")
                    error_msg.postValue(t.message)
                }
            })

        }
    }
}