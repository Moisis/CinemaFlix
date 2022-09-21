package com.example.cinemaflix.fragments

import android.annotation.SuppressLint
import android.content.Context.MODE_PRIVATE
import android.content.SharedPreferences
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.cinemaflix.MovieDetailActivity.Companion.favmovies
import com.example.cinemaflix.R
import com.example.cinemaflix.adapters.MovieAdapter4
import com.example.cinemaflix.models.Movie
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
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
        loaddata()
        if (favmovies.size==0){
            rv_movies_fav.visibility = View.GONE
            textView2.visibility = View.VISIBLE
            textView2.text = "No Items Added"
        }else {
            loaddata()
            textView2.visibility = View.GONE
            rv_movies_fav.visibility = View.VISIBLE
            rv_movies_fav.adapter = MovieAdapter4(favmovies.toList())
            rv_movies_fav.layoutManager = LinearLayoutManager(activity)
            rv_movies_fav.setHasFixedSize(true)
        }
        refresh.setOnClickListener {
            loaddata()
            if (favmovies.size==0){
                rv_movies_fav.visibility = View.GONE
                textView2.visibility = View.VISIBLE
                textView2.text = "No Items Added"
            }else {
                textView2.visibility = View.GONE
                rv_movies_fav.visibility = View.VISIBLE
                rv_movies_fav.adapter = MovieAdapter4(favmovies.toList())
                rv_movies_fav.layoutManager = LinearLayoutManager(activity)
                rv_movies_fav.setHasFixedSize(true)
            }

        }



    }

    private fun loaddata() {
        val sharedPreferences: SharedPreferences =  requireActivity().getSharedPreferences("shared preferences", MODE_PRIVATE)

        val gson = Gson()
        val json = sharedPreferences.getString("fav list", null)
        val type = object : TypeToken<MutableSet<Movie?>?>() {}.type
        favmovies = gson.fromJson<Any>(json, type) as MutableSet<Movie>

    }



}


