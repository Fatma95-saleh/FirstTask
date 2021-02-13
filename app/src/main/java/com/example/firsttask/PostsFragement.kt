package com.example.firsttask

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.firsttask.PostsAdapter.PostsAdapter
import com.example.firsttask.database.PostsData
import com.example.firsttask.database.Roomdb
import com.example.firsttask.postsviewmodel.PostsViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import retrofit2.HttpException


class PostsFragement : Fragment() {
    lateinit var postAdapter: PostsAdapter
    lateinit var postsRecycler: RecyclerView
    var postsArray: ArrayList<PostsData> = ArrayList()

    lateinit var viewModel: PostsViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel = ViewModelProvider(this).get(PostsViewModel::class.java)
        viewModel.getPosts()

    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_posts_fragement, container, false)

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initViews(view)

        ObservePosts()
    }

    fun ObservePosts() {
        viewModel.postsData.observe(viewLifecycleOwner, Observer {
            postsArray.addAll(it)
            postAdapter.notifyItemRangeInserted(postsArray.size + 1, it.size)

            // insert posts offline
            CoroutineScope(Dispatchers.IO).launch {
                var posts = Roomdb.getInstance(requireActivity())?.postsDao.getPosts()
                if (posts.size == 0) {
                    Roomdb.getInstance(requireActivity())?.postsDao.insertPosts(it)
                }
            }

        })
        viewModel.error_msg.observe(viewLifecycleOwner, Observer {
            CoroutineScope(Dispatchers.IO).launch {
                var posts = Roomdb.getInstance(requireActivity())?.postsDao.getPosts()
                if (posts.size > 0) {
                    postsArray.addAll(posts)
                    withContext(Dispatchers.Main) {
                        postAdapter.notifyDataSetChanged()
                    }
                }

            }
        })
    }


    fun initViews(view: View) {

        postsRecycler = view.findViewById(R.id.postRecycler)
        postsRecycler.layoutManager = LinearLayoutManager(activity, LinearLayoutManager.VERTICAL, false)
        postAdapter = PostsAdapter(postsArray)
        postsRecycler.adapter = postAdapter
    }


}