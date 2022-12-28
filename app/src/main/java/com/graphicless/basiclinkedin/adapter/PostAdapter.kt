package com.graphicless.basiclinkedin.adapter

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
import kotlinx.android.synthetic.main.item_post.view.*
import java.util.*

class PostAdapter(private var posts:List<PostModel>): RecyclerView.Adapter<PostAdapter.PostViewHolder>() {


    class PostViewHolder(val view: View) : RecyclerView.ViewHolder(view) {
        fun bind(item:PostModel){

            val postMessage = item.post_message
            var showMessage = postMessage
            try{
                 showMessage = postMessage.substring(0,100).plus("... read more")
            }catch (_: Exception)
            {

            }


            view.user_name.text = item.name
            view.connection_type.text = item.connection_type
            view.title.text = item.title
            view.time.text = item.time
            view.post.text = showMessage

            if(!Objects.equals(item.user_image, null)){
                Glide.with(view.context)
                    .asBitmap()
                    .load(item.user_image)
                    .into(view.user_image)
            }

            if(!Objects.equals(item.post_image, null)){
                Glide.with(view.context)
                    .asBitmap()
                    .load(item.post_image)
                    .into(view.post_image)
            }

            view.post.setOnClickListener{
                view.post.text = postMessage
            }

            view.user_name.setOnClickListener {
                val action  = HomeFragmentDirections.actionHomeFragmentToProfileFragment(fileName = item.user_name)
                view.findNavController().navigate(action)
            }

        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PostViewHolder {
        val layout = LayoutInflater
            .from(parent.context)
            .inflate(R.layout.item_post, parent, false)

        return PostViewHolder(layout)
    }

    override fun onBindViewHolder(holder: PostViewHolder, position: Int) {
        val item = posts[position]
        holder.bind(item)
    }

    override fun getItemCount(): Int {
        return posts.size
    }
}