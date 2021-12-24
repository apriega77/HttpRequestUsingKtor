package com.apriega77.httprequestusingktor

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.activity.compose.setContent
import androidx.compose.runtime.produceState
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.apriega77.httprequestusingktor.adapter.PostAdapter
import com.apriega77.httprequestusingktor.data.dto.PostResponse
import com.apriega77.httprequestusingktor.data.remote.PostService
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    private val client = PostService.create()
    private lateinit var postAdapter: PostAdapter
    private lateinit var viewModel: MainViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        initRecyclerView()
        initViewModel()


    }

    private fun initViewModel() {
        viewModel = ViewModelProvider(this).get(MainViewModel::class.java)
        viewModel.getPostObserverable().observe(this, Observer {
            if (it!=null) {
                Log.d("MainActivity", it.toString())
                postAdapter.postList = it.toMutableList()
                postAdapter.notifyDataSetChanged()
            }
        })

        viewModel.getPost()
    }

    private fun initRecyclerView() {

        rvPost.apply {
            layoutManager = LinearLayoutManager(this@MainActivity)
            val decoration = DividerItemDecoration(this@MainActivity, DividerItemDecoration.VERTICAL )
            addItemDecoration(decoration)
            postAdapter = PostAdapter()
            adapter = postAdapter
        }
    }




}