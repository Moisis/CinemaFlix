package com.example.cinemaflix

import android.os.Bundle
import android.view.View
import android.widget.ProgressBar
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.example.cinemaflix.fragments.HomeFragment
import com.example.cinemaflix.fragments.PopularFragment
import com.example.cinemaflix.fragments.SearchFragment
import com.example.cinemaflix.fragments.SettingsFragment
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.fragment_search.*


class MainActivity : AppCompatActivity() {

    private val homefragment = HomeFragment()
    private val searchfragment = SearchFragment()
    private val settingsfragment = SettingsFragment()
    private val popularFragment = PopularFragment()



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        switchfragment(homefragment)

        bottom_navigation.setOnNavigationItemSelectedListener {
            when(it.itemId){
                R.id.home -> {switchfragment(homefragment)
                    switchfragment2(popularFragment)
                }
                R.id.search -> {
                    switchfragment(searchfragment)
                    homefragment.onPause()
                    settingsfragment.onPause()

                }
                R.id.favourites ->{switchfragment(settingsfragment)
                    searchfragment.onPause()
                    homefragment.onPause()
                }
            }
            true
        }
    }

    private fun switchfragment(fragment: Fragment) {
        if (!fragment.isVisible) {
            val transaction = supportFragmentManager.beginTransaction()
            transaction.replace(R.id.fragmentholder, fragment)
            transaction.commit()
        }

    }
    private fun switchfragment2(fragment: Fragment) {
        val transaction = supportFragmentManager.beginTransaction()
        transaction.replace(R.id.fragmentholder2, fragment)
        transaction.commit()

    }


}


