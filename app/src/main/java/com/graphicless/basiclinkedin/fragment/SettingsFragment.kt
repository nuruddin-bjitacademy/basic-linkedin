package com.graphicless.basiclinkedin.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatDelegate
import com.graphicless.basiclinkedin.R
import com.graphicless.basiclinkedin.SharedPreference
import kotlinx.android.synthetic.main.fragment_settings.*

class SettingsFragment : Fragment() {



    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_settings, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val sharedPreference = SharedPreference(requireContext())

        if (sharedPreference.getValueString("theme")!=null) {
            val myTheme = sharedPreference.getValueString("theme")
            switch_theme.isChecked = myTheme != "light"
        }else{
            switch_theme.isChecked = false
        }

        switch_theme.setOnCheckedChangeListener { buttonView, isChecked ->
            if(isChecked){
                sharedPreference.save("theme", "dark")
                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES)
            }else{
                sharedPreference.save("theme", "light")
                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
            }
        }
    }

}