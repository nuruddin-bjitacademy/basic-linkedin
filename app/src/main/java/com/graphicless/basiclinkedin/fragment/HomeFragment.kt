package com.graphicless.basiclinkedin.fragment

import android.R.attr.data
import android.app.ProgressDialog
import android.os.Bundle
import android.os.Handler
import android.util.Log
import android.view.*
import androidx.appcompat.app.AppCompatDelegate
import androidx.core.content.ContentProviderCompat.requireContext
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.graphicless.basiclinkedin.R
import com.graphicless.basiclinkedin.adapter.PostAdapter
import com.graphicless.basiclinkedin.data.DataSource
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


    var handler: Handler = Handler()
    var progressDialog: ProgressDialog? = null

    var postList = mutableListOf<PostModel>()

    private var isLight = true

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(true)
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

//        LoadData()

//        postList = fetchPost().posts
        Log.d(TAG, "post: $postList")

        val dataset = DataSource().loadPosts()

        recyclerView.adapter = PostAdapter(dataset)
        recyclerView.hasFixedSize()
//        fetchPost().start()
        super.onViewCreated(view, savedInstanceState)
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.home_menu, menu)

        val layoutButton = menu.findItem(R.id.action_switch_theme)
        setIcon(layoutButton)
    }

    private fun setIcon(menuItem: MenuItem?) {
        if (menuItem == null)
            return

        menuItem.icon =
            if (isLight)
                ContextCompat.getDrawable(this.requireContext(), R.drawable.dark_theme)
            else ContextCompat.getDrawable(this.requireContext(), R.drawable.light_theme)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.action_switch_theme -> {
                // Sets isLinearLayoutManager (a Boolean) to the opposite value
                isLight = !isLight
                // Sets layout and icon
                chooseTheme(isLight)
                setIcon(item)

                return true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }

    private fun chooseTheme(isLight: Boolean) {
        if (isLight) {
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
        } else {
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES)
        }
    }


    private fun readData(): String {
        val temp = StringBuilder()
        try {
            val fin: FileInputStream = FileInputStream("posts.json")
            var a: Int

            while (fin.read().also { a = it } != -1) {
                temp.append(a.toChar())
            }
            fin.close()
        } catch (e: IOException) {
            e.printStackTrace()
        }

        return temp.toString()
    }

    fun LoadData(){
        val data: String = readData()
        val jsonObject:JSONObject = JSONObject(data)
        val postArray: JSONArray = jsonObject.getJSONArray("posts")

        for (i in 0 until postArray.length()) {
            val postObject = postArray.getJSONObject(i)
            val user_image = postObject.getString("user_image")
            val name = postObject.getString("name")
            val connection_type = postObject.getString("connection_type")
            val title = postObject.getString("title")
            val time = postObject.getString("time")
            val post_message = postObject.getString("post_message")
            val post_image = postObject.getString("post_image")
//            val post = PostModel(
////                user_image, name, connection_type, title, time, post_message, post_image
//            )
//            postList.add(post)
        }
    }



     class fetchPost : Thread()  {
        val posts = mutableListOf<PostModel>()
        var data = ""
        override fun run(){
//            handler.post(Runnable {
//                progressDialog = ProgressDialog(this@ExamActivity)
//                progressDialog.setMessage("Creating Questions")
//                progressDialog.setCancelable(false)
//                progressDialog.show()
//            })
            try {
                val url =  URL("https://api.npoint.io/78ccf8e7a584e435242f");
                val httpURLConnection: HttpURLConnection = url.openConnection() as HttpURLConnection
                val inputStream: InputStream = httpURLConnection.inputStream
                val bufferedReader = BufferedReader(InputStreamReader(inputStream))
                var line: String
                while (bufferedReader.readLine().also { line = it } != null) {
                    data += line
                }
                Log.d(TAG, "run: data: $data")
                if (data.isNotEmpty()) {
                    val jsonObject = JSONObject(data)
                    val postArray: JSONArray = jsonObject.getJSONArray("posts")

                    for (i in 0 until postArray.length()) {
                        val postObject = postArray.getJSONObject(i)
                        val user_image = postObject.getString("user_image")
                        val name = postObject.getString("name")
                        val connection_type = postObject.getString("connection_type")
                        val title = postObject.getString("title")
                        val time = postObject.getString("time")
                        val post_message = postObject.getString("post_message")
                        val post_image = postObject.getString("post_image")
//                        val post = PostModel(
////                            user_image, name, connection_type, title, time, post_message, post_image
//                        )
//                        posts.add(post)
//                        list.add(post)
                    }
                    Log.d(TAG, "run: post: $posts")
                    PostAdapter(posts)
                }
            } catch (e: IOException) {
                e.printStackTrace()
            } catch (e: JSONException) {
                e.printStackTrace()
            }
//            handler.post(Runnable {
//                if (progressDialog.isShowing()) {
//                    progressDialog.dismiss()
//                    adapter.updateData(model)
//                }
//            })
            fun a():MutableList<PostModel>{
                return posts
            }
        }
    }
//}
}