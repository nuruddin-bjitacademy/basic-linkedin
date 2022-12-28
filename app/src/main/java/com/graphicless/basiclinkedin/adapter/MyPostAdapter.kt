package com.graphicless.basiclinkedin.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.graphicless.basiclinkedin.R
import com.graphicless.basiclinkedin.fragment.HomeFragmentDirections
import com.graphicless.basiclinkedin.model.CreatePostModel
import com.graphicless.basiclinkedin.model.PostModel
import com.graphicless.basiclinkedin.viewmodel.PostViewModel
import kotlinx.android.synthetic.main.item_post.view.*
import java.util.*
import kotlin.collections.ArrayList

class MyPostAdapter(
    private val context: Context,
    private val myPostList: ArrayList<CreatePostModel>
) : RecyclerView.Adapter<MyPostAdapter.MyPostViewHolder>() {
    class MyPostViewHolder(private val view: View) : RecyclerView.ViewHolder(view) {
        fun bind(item: CreatePostModel){

            val postMessage = item.data
            var showMessage = postMessage
            try{
                showMessage = postMessage.substring(0,100).plus("... read more")
            }catch (_: Exception)
            {

            }


            view.user_name.text = "my_profile"
            view.connection_type.text = "You"
            view.title.text = "Android Developer"
            view.time.text = "12h"
            view.post.text = showMessage

//            if(!Objects.equals(item.user_image, null)){
//                Glide.with(view.context)
//                    .asBitmap()
//                    .load(item.user_image)
//                    .into(view.user_image);
//            }
//
//            if(!Objects.equals(item.post_image, null)){
//                Glide.with(view.context)
//                    .asBitmap()
//                    .load(item.post_image)
//                    .into(view.post_image);
//            }

            view.post.setOnClickListener{
                view.post.text = postMessage
            }

        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyPostViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_post, parent, false)
        return MyPostViewHolder(view)
    }

    override fun onBindViewHolder(holder: MyPostViewHolder, position: Int) {
        val post = myPostList[position]
//        holder.itemView.post.text = post.data
        holder.bind(post)
    }

    override fun getItemCount(): Int {
        return myPostList.size
    }
}