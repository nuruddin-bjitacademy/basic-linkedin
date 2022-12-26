package com.graphicless.basiclinkedin

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.setupActionBarWithNavController
import com.graphicless.basiclinkedin.fragment.HomeFragment
import com.graphicless.basiclinkedin.fragment.ProfileFragment
import com.graphicless.basiclinkedin.fragment.SettingsFragment
import kotlinx.android.synthetic.main.activity_main.*

private const val TAG = "MainActivity"
class MainActivity : AppCompatActivity() {

    private lateinit var navController: NavController

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val navHostFragment = supportFragmentManager
            .findFragmentById(R.id.nav_host_fragment) as NavHostFragment
        navController = navHostFragment.navController
        setupActionBarWithNavController(navController)
        
        bottom_navigation_view.setOnNavigationItemSelectedListener {
            when(it.itemId){
                R.id.menu_home->setCurrentFragment(HomeFragment())
                R.id.menu_profile->setCurrentFragment(ProfileFragment())
                R.id.menu_settings->setCurrentFragment(SettingsFragment())

            }
            true
        }
    }

    private fun setCurrentFragment(fragment: Fragment)=
        supportFragmentManager.beginTransaction().apply {
            replace(R.id.nav_host_fragment,fragment)
            commit()
        }



    override fun onSupportNavigateUp(): Boolean {
        return navController.navigateUp() || super.onSupportNavigateUp()
    }
}