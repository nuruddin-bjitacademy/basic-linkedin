package com.graphicless.basiclinkedin.fragment

import android.annotation.SuppressLint
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.navArgs
import com.bumptech.glide.Glide
import com.graphicless.basiclinkedin.R
import kotlinx.android.synthetic.main.fragment_profile.view.*
import org.json.JSONObject
import java.io.File
import java.io.IOException
import java.io.InputStream
import java.util.*

private const val TAG = "ProfileFragment"
class ProfileFragment : Fragment() {

    private val args: ProfileFragmentArgs by navArgs()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_profile, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val data: String = readData()
        Log.d(TAG, "data: $data")
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



    }

    private fun readData(): String {
        val pathPrefix = "/data/data/com.graphicless.basiclinkedin/files/"
        var fileName = args.fileName
        if(fileName.isEmpty())
            fileName = "my_profile"
        val fileExtension = ".json"
        val filePath = pathPrefix + fileName + fileExtension
        var inputString = ""
        try {
            val inputStream: InputStream = File (filePath).inputStream()
            inputString = inputStream.reader().use {it.readText()}
            Log.d(TAG, "readData: $inputString")
        } catch (e: IOException) {
            e.printStackTrace()
        }

        return inputString.toString()
    }

}