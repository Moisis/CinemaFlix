package com.example.cinemaflix.fragments



import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ProgressBar
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.cinemaflix.MainActivity
import com.example.cinemaflix.R
import com.example.cinemaflix.adapters.MovieAdapter
import com.example.cinemaflix.api.ApiInterface
import com.example.cinemaflix.api.MovieApiService
import com.example.cinemaflix.models.Movie
import com.example.cinemaflix.models.MovieResponse
import com.google.android.material.tabs.TabLayout
import kotlinx.android.synthetic.main.fragment_home.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class HomeFragment() : Fragment() {




    private val topratedfragment = TopRatedFragment()
    private val popularFragment = PopularFragment()
    private val nowPlayingFragment = NowPlayingFragment()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
         return inflater.inflate(R.layout.fragment_home, container, false)

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        switchfragment2(popularFragment)


        val tabLayout :TabLayout = view.findViewById(R.id.tabhome)


        tabLayout.addOnTabSelectedListener(object : TabLayout.OnTabSelectedListener {

            override fun onTabSelected(tab: TabLayout.Tab) {

                when(tab.position){
                    0->{switchfragment2(popularFragment)
                         topratedfragment.onPause()
                        nowPlayingFragment.onPause()
                    }
                    1->{switchfragment2(topratedfragment)
                    popularFragment.onPause()
                        nowPlayingFragment.onPause()

                    }
                    2->{switchfragment2(nowPlayingFragment)
                        topratedfragment.onPause()
                        popularFragment.onPause()
                    }

                }
            }

            override fun onTabUnselected(tab: TabLayout.Tab) {}
            override fun onTabReselected(tab: TabLayout.Tab) {}
        })




    }


    private fun switchfragment2(fragment: Fragment) {
            val transaction = parentFragmentManager.beginTransaction()
            transaction.replace(R.id.fragmentholder2, fragment)
            transaction.commit()

    }

}








