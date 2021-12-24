package com.apriega77.httprequestusingktor

import androidx.compose.runtime.produceState
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.apriega77.httprequestusingktor.data.dto.PostResponse
import com.apriega77.httprequestusingktor.data.remote.PostService

class MainViewModel : ViewModel() {

    lateinit var  postLiveData : MutableLiveData<List<PostResponse>>

    init {
        postLiveData = MutableLiveData()
    }

    fun getPostObserverable() : MutableLiveData<List<PostResponse>> {
        return postLiveData
    }

     suspend fun getPost() {
        PostService.create().getPosts().let {
            if (!it.isNullOrEmpty()) {
                postLiveData.postValue(it)
            } else{
                postLiveData.postValue(null)
            }
        }


    }
}