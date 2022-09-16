package com.example.cinemaflix

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.Fragment
import com.example.cinemaflix.fragments.HomeFragment
import com.example.cinemaflix.fragments.SearchFragment
import com.example.cinemaflix.fragments.SettingsFragment

import kotlinx.android.synthetic.main.activity_main.*



class MainActivity : AppCompatActivity() {

    private val homefragment = HomeFragment()
    private val searchfragment = SearchFragment()
    private val settingsfragment = SettingsFragment()

    
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        switchfragment(homefragment)

        bottom_navigation.setOnNavigationItemSelectedListener {
            when(it.itemId){
                R.id.home -> switchfragment(homefragment)
                R.id.search -> switchfragment(searchfragment)
                R.id.settings ->switchfragment(settingsfragment)
            }
            true
        }





    }

    private fun switchfragment(fragment: Fragment) {
        val transaction = supportFragmentManager.beginTransaction()
        transaction.replace(R.id.fragmentholder, fragment)
        transaction.commit()

    }


}


