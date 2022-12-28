package com.graphicless.basiclinkedin.viewmodel

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.graphicless.basiclinkedin.model.CreatePostModel

private const val TAG = "PostViewModel"
class PostViewModel: ViewModel() {
    val list = MutableLiveData<ArrayList<CreatePostModel>>()
    val newList = arrayListOf<CreatePostModel>()

    fun addPost(post: CreatePostModel){
        newList.add(post)
        list.value = newList
        Log.d(TAG, "addPost: "+ newList.size)
    }

    fun removeBlog(blog: CreatePostModel){
        newList.remove(blog)
        list.value = newList
    }
}