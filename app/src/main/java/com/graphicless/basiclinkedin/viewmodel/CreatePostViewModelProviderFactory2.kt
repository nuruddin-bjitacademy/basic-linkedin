package com.graphicless.basiclinkedin.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider

class CreatePostViewModelProviderFactory2 : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if(modelClass.isAssignableFrom(PostViewModel::class.java)){
            return PostViewModel() as T
        }
        throw IllegalAccessError("unknown")
    }
}