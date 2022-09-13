package com.example.cinemaflix

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView
import com.bumptech.glide.Glide
import com.example.cinemaflix.api.ApiInterface
import com.example.cinemaflix.api.MovieApiService
import com.example.cinemaflix.models.MovieDetail
import com.example.cinemaflix.models.MovieDetailResponse
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.activity_movie_detail.*
import kotlinx.android.synthetic.main.movie_item.view.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class MovieDetailActivity : AppCompatActivity() {

    private val IMAGE_BASE = "https://image.tmdb.org/t/p/w500/"
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_movie_detail)
        val imageView: ImageView = findViewById(R.id.movie_poster)
        val imageviewdata = intent.extras?.getString("MovieId")
        val textView : TextView = findViewById(R.id.movie_title)
        Glide.with(imageView).load(IMAGE_BASE + imageviewdata).into(imageView)
        textView.text = imageviewdata

    }



}



