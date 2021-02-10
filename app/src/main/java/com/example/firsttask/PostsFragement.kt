package com.example.firsttask

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.firsttask.PostsAdapter.PostsAdapter
import com.example.firsttask.database.PostsData
import com.example.firsttask.database.Roomdb
import com.example.firsttask.postsviewmodel.PostsViewModel


class PostsFragement : Fragment() {
    lateinit var postAdapter: PostsAdapter
    lateinit var postsRecycler: RecyclerView
    var postsArray:ArrayList<PostsData> = ArrayList()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_posts_fragement, container, false)

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        //  postsArray.add(PostData(1,1,"hfh","jffjjj"))
        //  postsArray.add(PostData(1,1,"hfh","jffjjj"))
        //  postsArray.add(PostData(1,1,"hfh","jffjjj"))

        initViews(view)
        getPostsUsingMvvm()
        localPosts()

    }


    fun initViews(view:View){

        postsRecycler=view.findViewById(R.id.postRecycler)
        postsRecycler.layoutManager= LinearLayoutManager(activity, LinearLayoutManager.VERTICAL,false)
        postAdapter= PostsAdapter(postsArray)
        postsRecycler.adapter=postAdapter
    }

    fun getPostsUsingMvvm(){
        var viewmodel=ViewModelProviders.of(this).get(PostsViewModel::class.java)
        viewmodel.getPostsObserver().observe(viewLifecycleOwner,Observer <ArrayList<PostsData>>{

            if(it!=null){

                postsArray.addAll(it)
                postAdapter.notifyDataSetChanged()

            }

        })

        viewmodel.getPosts()

    }

    fun localPosts(){
        var viewmodel=ViewModelProviders.of(this).get(PostsViewModel::class.java)
        viewmodel.getPostsLocal().observe(viewLifecycleOwner,Observer <ArrayList<PostsData>>{

            if(it!=null){

                postsArray.addAll(it)
                postAdapter.notifyDataSetChanged()
            }

        })

//        viewmodel.getPosts()

    }



     }