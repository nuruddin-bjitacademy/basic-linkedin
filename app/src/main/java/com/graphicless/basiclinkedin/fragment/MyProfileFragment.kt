package com.graphicless.basiclinkedin.fragment

import android.app.Application
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import com.bumptech.glide.Glide
import com.graphicless.basiclinkedin.R
import com.graphicless.basiclinkedin.adapter.MyPostAdapter
import com.graphicless.basiclinkedin.model.CreatePostModel
import com.graphicless.basiclinkedin.viewmodel.CreatePostViewModelProviderFactory
import com.graphicless.basiclinkedin.viewmodel.CreatePostViewModelProviderFactory2
import com.graphicless.basiclinkedin.viewmodel.PostViewModel
import kotlinx.android.synthetic.main.fragment_my_profile.*
import kotlinx.android.synthetic.main.fragment_profile.view.*
import org.json.JSONObject
import java.io.File
import java.io.IOException
import java.io.InputStream
import java.util.*
import kotlin.collections.ArrayList

private const val TAG = "MyProfileFragment"
class MyProfileFragment : Fragment() {

    private lateinit var layoutManager: LinearLayoutManager
    private lateinit var viewModel: PostViewModel
    private lateinit var viewModel2: PostViewModel
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_my_profile, container, false)
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val data: String = readData()
        val jsonObject: JSONObject = JSONObject(data)

        val coverPic = jsonObject.getString("cover_pic")
        val profilePic = jsonObject.getString("profile_pic")
        val name = jsonObject.getString("name")
        val title = jsonObject.getString("title")
        val workCompany = jsonObject.getString("work_company")
        val workPlace = jsonObject.getString("work_place")
        val personalWebsite = jsonObject.getString("personal_website")
        val followers = "" + jsonObject.getInt("followers") +" followers"
        val connections = jsonObject.getInt("connections")
        if(connections >=500)
            view.connections.text = "500+ connections"
        else
            view.connections.text = "" + connections + " connections"

        if(!Objects.equals(coverPic, null)){
            Glide.with(view.context)
                .asBitmap()
                .load(coverPic)
                .into(view.cover_pic);
        }
        if(!Objects.equals(profilePic, null)){
            Glide.with(view.context)
                .asBitmap()
                .load(profilePic)
                .into(view.profile_pic);
        }

        view.name.text = name.toString()
        view.profile_title.text = title.toString()
        view.work_company.text = workCompany.toString()
        view.work_place.text = workPlace.toString()
        view.personal_website.text = personalWebsite.toString()
        view.followers.text = followers.toString()

//        val factory = CreatePostViewModelProviderFactory()
//        viewModel = ViewModelProvider(this, factory)[PostViewModel::class.java]
//
//        val factory2 = CreatePostViewModelProviderFactory2()
//        viewModel2  = ViewModelProviders.of(this, factory2)[PostViewModel::class.java]
//        viewModel = ViewModelProviders.of(this,factory)
        viewModel = ViewModelProvider(requireActivity())[PostViewModel::class.java]


//        layoutManager = LinearLayoutManager(requireContext())
//        recycler_view.layoutManager = layoutManager
        observeData()

//        val postList = ArrayList<CreatePostModel>()
//        val one = CreatePostModel("one", "date1")
//        val two = CreatePostModel("two", "date1")
//        val three = CreatePostModel("three", "date1")
//        postList.add(one)
//        postList.add(two)
//        postList.add(three)
//        recycler_view.adapter = MyPostAdapter(requireContext(), viewModel, postList)
//        recycler_view.adapter?.notifyDataSetChanged()
//
//        Log.d(TAG, "list: ${viewModel.list.value}")

    }

    private fun observeData() {
        viewModel.list.observe(viewLifecycleOwner) {
            recycler_view.layoutManager = LinearLayoutManager(requireContext())
            recycler_view.adapter = MyPostAdapter(requireContext(),  it)
            Log.d(TAG, "observeData: called")
        }
    }

    private fun readData(): String {
        val pathPrefix = "/data/data/com.graphicless.basiclinkedin/files/"
        var fileName = "my_profile"
        if(fileName.isEmpty())
            fileName = "my_profile"
        val fileExtension = ".json"
        val filePath = pathPrefix + fileName + fileExtension
        var inputString = ""
        try {
            val inputStream: InputStream = File (filePath).inputStream()
            inputString = inputStream.reader().use {it.readText()}
        } catch (e: IOException) {
            e.printStackTrace()
        }

        return inputString.toString()
    }
}