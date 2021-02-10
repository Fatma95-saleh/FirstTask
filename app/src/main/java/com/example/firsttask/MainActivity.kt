package com.example.firsttask

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle

class MainActivity : AppCompatActivity() {
//    lateinit var postAdapter:PostsAdapter
//    lateinit var postsRecycler:RecyclerView
//    var postsArray:ArrayList<PostsData> = ArrayList()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

//        postsArray.add(PostData(1,1,"hfh","jffjjj"))
//        postsArray.add(PostData(1,1,"hfh","jffjjj"))
//        postsArray.add(PostData(1,1,"hfh","jffjjj"))
//        initViews()
//        getPostsFromViewModel()

         var postsFragement=PostsFragement()
        var commentsFragemets=CommentsFragement()

        supportFragmentManager.beginTransaction().apply {

                replace(R.id.fragmentContainer,postsFragement)
                commit()

        }
    }

//    fun initViews(){
//
//        postsRecycler=findViewById(R.id.postRecycler)
//        postsRecycler.layoutManager=LinearLayoutManager(this,LinearLayoutManager.VERTICAL,false)
//        postAdapter= PostsAdapter(postsArray)
//        postsRecycler.adapter=postAdapter
//    }
//
//    fun getPostsFromViewModel(){
//
//        var viewmodel= ViewModelProviders.of(this).get(PostsViewModel::class.java)
//        viewmodel.postsData.observe(this, Observer<ArrayList<PostsData>>{
//
//              if (it!==null){
//
//                  postsArray.addAll(it)
//                  postAdapter.notifyItemRangeInserted(postsArray.size + 1 ,it.size!!)
//
//
//              }
//            viewmodel.getPostsRequest()
//
//        })
//
//
//
//
//    }

}