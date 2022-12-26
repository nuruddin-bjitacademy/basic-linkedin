package com.graphicless.basiclinkedin.adapter

import android.content.Context
import android.media.Image
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.core.widget.ImageViewCompat
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.graphicless.basiclinkedin.R
import com.graphicless.basiclinkedin.model.PostModel
import kotlinx.android.synthetic.main.item_post.view.*
import java.util.*

class PostAdapter(private var posts:List<PostModel>): RecyclerView.Adapter<PostAdapter.PostViewHolder>() {


    class PostViewHolder(val view: View) : RecyclerView.ViewHolder(view) {
        fun bind(item:PostModel){

//            val userImageUrl = item.user_image
//            val postImageUrl = item.post_image
//
//            view.user_name.text = item.name
//            view.connection_type.text = item.connection_type
//            view.title.text = item.title
//            view.time.text = item.time
//            view.post.text = item.post_message

//            val title = view.context.getString(item.title)
//            val showTitle = title.su

            val postMessage = view.context.getString(item.post_message)
            var showMessage = postMessage
            try{
                 showMessage = postMessage.substring(0,100).plus("... read more")
            }catch (_: Exception)
            {

            }


            view.user_name.text = view.context.getString(item.name)
            view.connection_type.text = view.context.getString(item.connection_type)
            view.title.text = view.context.getString(item.title)
            view.time.text = view.context.getString(item.time)
            view.post.text = showMessage

            if(!Objects.equals(view.context.getString(item.user_image), null)){
                Glide.with(view.context)
                    .asBitmap()
                    .load(view.context.getString(item.user_image))
                    .into(view.user_image);
            }

            if(!Objects.equals(view.context.getString(item.post_image), null)){
                Glide.with(view.context)
                    .asBitmap()
                    .load(view.context.getString(item.post_image))
                    .into(view.post_image);
            }

            view.post.setOnClickListener{
                view.post.text = postMessage
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