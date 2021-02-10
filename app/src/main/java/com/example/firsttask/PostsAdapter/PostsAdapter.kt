package com.example.firsttask.PostsAdapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.firsttask.R
import com.example.firsttask.database.PostsData


class PostsAdapter(var arr:ArrayList<PostsData>): RecyclerView.Adapter<PostsAdapter.ViewHolder>() {
    class ViewHolder(view:View):RecyclerView.ViewHolder(view)  {

           var postTitle: TextView
            var postBody: TextView

         init {
             postTitle=view.findViewById(R.id.postTitle)
             postBody=view.findViewById(R.id.postBody)
         }
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {

        val v= LayoutInflater.from(parent.context).inflate(R.layout.posts_row,parent,false)

        return ViewHolder(v)
    }

    override fun getItemCount(): Int {
        return arr.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

        var arrayData:PostsData=arr[position]
        holder.postTitle.text=arrayData.title.toString()
        holder.postBody.text=arrayData.body.toString()

    }
}