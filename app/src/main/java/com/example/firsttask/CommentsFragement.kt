package com.example.firsttask

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.firsttask.commentsadapter.CommentsAdapter
import com.example.firsttask.commentsadapter.CommentsData
import com.example.firsttask.network.NetworkManager
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.awaitResponse


class CommentsFragement : Fragment() {

    var arr:ArrayList<CommentsData> = ArrayList()
    lateinit var  commentsRecycler:RecyclerView
    lateinit var  commentsAdapter:CommentsAdapter



    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_comments_fragement2, container, false)
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)



        initviews(view)

        GlobalScope.launch(Dispatchers.IO) {

            val response=NetworkManager.retrofitConnection().getComments().awaitResponse()
            if(response.isSuccessful){

                arr.addAll(response.body()!!)
                withContext(Dispatchers.Main){
                    commentsAdapter.notifyDataSetChanged()

                }

            }

        }

       // getDataFromRetrofit()

    }

    fun initviews(view: View){

        commentsRecycler=view.findViewById(R.id.commentsRecycler)
        commentsRecycler.layoutManager=LinearLayoutManager(activity,LinearLayoutManager.VERTICAL,false)
        commentsAdapter= CommentsAdapter(arr)
        commentsRecycler.adapter=commentsAdapter

    }
/*
    fun getDataFromRetrofit(){

        NetworkManager.retrofitConnection().getComments().enqueue(object :Callback<ArrayList<CommentsData>>{
            override fun onResponse(call: Call<ArrayList<CommentsData>>, response: Response<ArrayList<CommentsData>>) {

                if(response.isSuccessful){

                    arr.addAll(response.body()!!)
                    commentsAdapter.notifyDataSetChanged()
                    Log.e("connection","success")

                }
            }
            override fun onFailure(call: Call<ArrayList<CommentsData>>, t: Throwable) {

                Log.e("connection","failed")

            } })
    }

 */

    }
