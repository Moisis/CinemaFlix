package com.example.cinemaflix.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ProgressBar
import android.widget.TextView
import androidx.appcompat.widget.SearchView
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.cinemaflix.R
import com.example.cinemaflix.adapters.MovieAdapter
import com.example.cinemaflix.api.ApiInterface
import com.example.cinemaflix.api.MovieApiService
import com.example.cinemaflix.models.Movie
import com.example.cinemaflix.models.MovieResponse
import kotlinx.android.synthetic.main.fragment_search.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class SearchFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_search, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val progress : ProgressBar = view.findViewById(R.id.progressBar)
        progress.visibility = View.GONE
        val searchview: SearchView = view.findViewById(R.id.searchView)
        searchview.clearFocus()
        searchview.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(p0: String?): Boolean {
                getsearches(p0)
                progress.visibility = View.GONE
                return true
            }

            override fun onQueryTextChange(p0: String?): Boolean {
                progress.visibility = View.VISIBLE
                clearsearches()

                return false

            }


        })
    }



    private fun clearsearches() {
    rv_movies_search.visibility = View.GONE
    }

    private fun getsearches(p0: String?) {
        rv_movies_search.visibility = View.VISIBLE
        rv_movies_search.layoutManager = LinearLayoutManager(this.context)
        rv_movies_search.setHasFixedSize(true)
        if (p0 != null) {
            getsearches1(p0){ movies: List<Movie> ->
                rv_movies_search.adapter = MovieAdapter(movies) }}
        else{
                rv_movies_search.visibility = View.GONE


            }
        }

    }

    fun getsearches1(p0:String,callback: (List<Movie>) -> Unit) {
        val apiService = MovieApiService.getInstance().create(ApiInterface::class.java)
        apiService.getSearches(p0).enqueue(object : Callback<MovieResponse> {
            override fun onFailure(call: Call<MovieResponse>, t: Throwable) {

            }

            override fun onResponse(call: Call<MovieResponse>, response: Response<MovieResponse>) {

                return callback(response.body()!!.movies)
            }

        })
    }



