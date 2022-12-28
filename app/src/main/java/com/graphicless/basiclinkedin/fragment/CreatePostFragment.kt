package com.graphicless.basiclinkedin.fragment

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.content.res.ResourcesCompat
import androidx.core.widget.addTextChangedListener
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.bumptech.glide.Glide
import com.graphicless.basiclinkedin.R
import com.graphicless.basiclinkedin.model.CreatePostModel
import com.graphicless.basiclinkedin.viewmodel.PostViewModel
import kotlinx.android.synthetic.main.fragment_create_post.*
import kotlinx.android.synthetic.main.fragment_create_post.profile_pic
import kotlinx.android.synthetic.main.fragment_my_profile.*
import kotlinx.android.synthetic.main.item_post.view.*

class CreatePostFragment : Fragment() {

    lateinit var viewModel: PostViewModel
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_create_post, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel = ViewModelProvider(this)[PostViewModel::class.java]
        val profilePic = context?.resources?.getString(R.string.profile_pic)
        Glide.with(view.context)
            .asBitmap()
            .load(profilePic)
            .into(profile_pic)



        et_post.addTextChangedListener {
            btn_post.isEnabled = et_post.text?.isEmpty() != true
        }
        btn_post.setOnClickListener {
            val input = et_post.text.toString()
            Toast.makeText(requireContext(), input.toString(), Toast.LENGTH_SHORT).show()
            val post = CreatePostModel(input, "1.1.1")
            viewModel.addPost(post)
            findNavController().navigate(R.id.action_createPostFragment_to_myProfileFragment)
        }
    }

}