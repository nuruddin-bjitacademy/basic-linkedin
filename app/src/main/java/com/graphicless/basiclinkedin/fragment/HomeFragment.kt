package com.graphicless.basiclinkedin.fragment

import android.os.Bundle
import android.util.Log
import android.view.*
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.graphicless.basiclinkedin.R
import com.graphicless.basiclinkedin.adapter.PostAdapter
import com.graphicless.basiclinkedin.model.CreatePostModel
import com.graphicless.basiclinkedin.model.PostModel
import org.json.JSONArray
import org.json.JSONException
import org.json.JSONObject
import java.io.*
import java.net.HttpURLConnection
import java.net.URL

private const val TAG = "HomeFragment"

class HomeFragment : Fragment() {

    private lateinit var recyclerView: RecyclerView
    var postList = mutableListOf<PostModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(true)
        postList.clear()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_home, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        recyclerView = view.findViewById(R.id.recycler_view)
        recyclerView.layoutManager = LinearLayoutManager(context)

        LoadData()

        recyclerView.adapter = PostAdapter(postList)
        recyclerView.hasFixedSize()

        super.onViewCreated(view, savedInstanceState)
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.home_menu, menu)

        val layoutButton = menu.findItem(R.id.action_switch_theme)
    }



    override fun onOptionsItemSelected(item: MenuItem): Boolean {
            return super.onOptionsItemSelected(item)

    }

    private fun readData(): String {
//        val pathPrefix = context?.filesDir?.path
        val pathPrefix = "/data/data/com.graphicless.basiclinkedin/files/"
        val fileName = "posts"
        val fileExtension = ".json"
        val filePath = pathPrefix + fileName + fileExtension
        var inputString = ""
        try {
            val inputStream: InputStream = File(filePath).inputStream()
            inputString = inputStream.reader().use { it.readText() }
        } catch (e: IOException) {
            e.printStackTrace()
        }

        return inputString
    }

    fun LoadData() {
        val data: String = readData()
        val jsonObject: JSONObject = JSONObject(data)

        val postArray: JSONArray = jsonObject.getJSONArray("posts")

        for (i in 0 until postArray.length()) {
            val postObject = postArray.getJSONObject(i)
            val profilePic = postObject.getString("profile_pic")
            val userName = postObject.getString("user_name")
            val name = postObject.getString("name")
            val connectionType = postObject.getString("connection_type")
            val title = postObject.getString("title")
            val time = postObject.getString("time")
            val postData = postObject.getString("post_data")
            val postImage = postObject.getString("post_image")

            val post = PostModel(userName, profilePic, name,connectionType, title, time, postData, postImage )

            postList.add(post)
        }
    }

}