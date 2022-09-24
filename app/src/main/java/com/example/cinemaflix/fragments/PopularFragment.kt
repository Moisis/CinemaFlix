package com.example.cinemaflix.fragments

import android.os.Bundle
import android.os.Parcelable
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ProgressBar
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.cinemaflix.R
import com.example.cinemaflix.adapters.MovieAdapter
import com.example.cinemaflix.api.ApiInterface
import com.example.cinemaflix.api.MovieApiService
import com.example.cinemaflix.models.Movie
import com.example.cinemaflix.models.MovieResponse
import kotlinx.android.synthetic.main.fragment_now_playing.*
import kotlinx.android.synthetic.main.fragment_popular.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class PopularFragment() : Fragment() {
    val error : Fragment = ErrorFragment()
    private var pageid = 0
    var popularposition : Parcelable? = null
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_popular, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        progressBar2.visibility = View.VISIBLE
        addpopular()

        var pastVisiblesItems: Int
        var visibleItemCount: Int
        var totalItemCount: Int
        val mLayoutManager = LinearLayoutManager(this.activity)
        popular_movies_list.layoutManager = mLayoutManager
        popular_movies_list.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                if (dy > 0) { //check for scroll down
                    visibleItemCount = mLayoutManager.childCount
                    totalItemCount = mLayoutManager.itemCount
                    pastVisiblesItems = mLayoutManager.findFirstVisibleItemPosition()
                        if (visibleItemCount + pastVisiblesItems >= totalItemCount/2) {
                            popularposition = mLayoutManager.onSaveInstanceState()
                        addpopular()

                    }
                }
            }
        })

    }
    fun addpopular(){
        if(pageid<=50) {
            pageid++
            progressBar2.visibility = View.VISIBLE
            getMovieData(pageid) { movies: List<Movie> ->
                popular_movies_list?.adapter = MovieAdapter(movies)
            }

        }
}

    private fun getMovieData(pageid: Int, callback: (List<Movie>) -> Unit) {
        val apiService = MovieApiService.getInstance().create(ApiInterface::class.java)
        apiService.getMovieList(pageid).enqueue(object : Callback<MovieResponse> {
            override fun onFailure(call: Call<MovieResponse>, t: Throwable) {
                val transaction = parentFragmentManager.beginTransaction()
                transaction.replace(R.id.fragmentholder2, error)
                transaction.commit()

            }

            override fun onResponse(call: Call<MovieResponse>, response: Response<MovieResponse>) {

                progressBar2?.visibility = View.GONE
                popular_movies_list?.layoutManager?.onRestoreInstanceState(popularposition)
                return callback(response.body()!!.movies)
            }

        })
    }

        }
