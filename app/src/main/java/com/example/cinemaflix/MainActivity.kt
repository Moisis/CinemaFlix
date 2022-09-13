package com.example.cinemaflix

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.cinemaflix.adapters.MovieAdapter
import com.example.cinemaflix.api.ApiInterface
import com.example.cinemaflix.api.MovieApiService
import com.example.cinemaflix.models.Movie
import com.example.cinemaflix.models.MovieResponse
import kotlinx.android.synthetic.main.activity_main.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        rv_movies_list.layoutManager = LinearLayoutManager(this)
        rv_movies_list.setHasFixedSize(true)
        getMovieData { movies: List<Movie> ->
            rv_movies_list.adapter = MovieAdapter(movies)
        }


        val textview1: TextView = findViewById(R.id.popular1)
        val textview2: TextView = findViewById(R.id.toprated)
        val textview3: TextView = findViewById(R.id.Upcoming)

        textview1.setOnClickListener {
            rv_movies_list.layoutManager = LinearLayoutManager(this)
            rv_movies_list.setHasFixedSize(true)
            getMovieData { movies: List<Movie> ->
                rv_movies_list.adapter = MovieAdapter(movies)
            }
        }
        textview2.setOnClickListener {
            rv_movies_list.layoutManager = LinearLayoutManager(this)
            rv_movies_list.setHasFixedSize(true)
            getMovieData2 { movies: List<Movie> ->
                rv_movies_list.adapter = MovieAdapter(movies)
            }
        }
        textview3.setOnClickListener {
            rv_movies_list.layoutManager = LinearLayoutManager(this)
            rv_movies_list.setHasFixedSize(true)
            getMovieData3 { movies: List<Movie> ->
                rv_movies_list.adapter = MovieAdapter(movies)
            }
        }


    }

    fun getMovieData(callback: (List<Movie>) -> Unit) {
        val apiService = MovieApiService.getInstance().create(ApiInterface::class.java)
        apiService.getMovieList().enqueue(object : Callback<MovieResponse> {
            override fun onFailure(call: Call<MovieResponse>, t: Throwable) {


            }

            override fun onResponse(call: Call<MovieResponse>, response: Response<MovieResponse>) {

                return callback(response.body()!!.movies)
            }

        })
    }

    fun getMovieData2(callback: (List<Movie>) -> Unit) {
        val apiService = MovieApiService.getInstance().create(ApiInterface::class.java)
        apiService.getMovieList2().enqueue(object : Callback<MovieResponse> {
            override fun onFailure(call: Call<MovieResponse>, t: Throwable) {

            }

            override fun onResponse(call: Call<MovieResponse>, response: Response<MovieResponse>) {
                return callback(response.body()!!.movies)
            }

        })
    }

    fun getMovieData3(callback: (List<Movie>) -> Unit) {
        val apiService = MovieApiService.getInstance().create(ApiInterface::class.java)
        apiService.getMovieList3().enqueue(object : Callback<MovieResponse> {
            override fun onFailure(call: Call<MovieResponse>, t: Throwable) {

            }

            override fun onResponse(call: Call<MovieResponse>, response: Response<MovieResponse>) {
                return callback(response.body()!!.movies)
            }

        })
    }
}
