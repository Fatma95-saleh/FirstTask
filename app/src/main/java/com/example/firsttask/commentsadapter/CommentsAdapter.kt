package com.example.firsttask.commentsadapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.firsttask.R

class CommentsAdapter (val commentsData:ArrayList<CommentsData>):RecyclerView.Adapter<CommentsAdapter.ViewHolder>(){
    class ViewHolder(view:View):RecyclerView.ViewHolder(view) {

        var commentName:TextView
        var commentEmail:TextView
        var commentBody:TextView

        init {
            commentName=view.findViewById(R.id.commentName)
            commentEmail=view.findViewById(R.id.commentEmail)
            commentBody=view.findViewById(R.id.commentBody)
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CommentsAdapter.ViewHolder {

        val inflate=LayoutInflater.from(parent.context).inflate(R.layout.comments_row,parent,false)
        return  ViewHolder(inflate)

    }

    override fun getItemCount(): Int {

        return commentsData.size
    }

    override fun onBindViewHolder(holder: CommentsAdapter.ViewHolder, position: Int) {

        var commentArray:CommentsData=commentsData[position]
        holder.commentName.text=commentArray.name
        holder.commentEmail.text=commentArray.email
        holder.commentBody.text=commentArray.body


    }


}