package com.example.cinemaflix

import android.media.Image
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView
import android.widget.RatingBar
import android.widget.TextView
import com.bumptech.glide.Glide



const val MOVIE_BACKDROP = "extra_movie_backdrop"
const val MOVIE_POSTER = "extra_movie_poster"
const val MOVIE_TITLE = "extra_movie_title"
const val MOVIE_RATING = "extra_movie_rating"
const val MOVIE_RELEASE_DATE = "extra_movie_release_date"
const val MOVIE_OVERVIEW = "extra_movie_overview"
class MovieDetailActivity : AppCompatActivity() {


    private val IMAGE_BASE = "https://image.tmdb.org/t/p/w500/"
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_movie_detail)

        val Movieposter : ImageView = findViewById(R.id.movie_poster)
        val Moviebackdrop : ImageView = findViewById(R.id.movie_backdrop)
        val Movietitle : TextView = findViewById(R.id.movie_title)
        val Movierelease : TextView = findViewById(R.id.movie_release_date)
        val Movieoverview : TextView = findViewById(R.id.movie_overview)
        val Movierating : RatingBar = findViewById(R.id.movie_rating)

        val Movieposterdata = intent?.extras?.getString(MOVIE_POSTER)
        val Moviebackdropdata   = intent?.extras?.getString(MOVIE_BACKDROP)
        val Movietitledata  = intent?.extras?.getString(MOVIE_TITLE)
        val Moviereleasedata  = intent?.extras?.getString(MOVIE_RELEASE_DATE)
        val Movieoverviewdata  = intent?.extras?.getString(MOVIE_OVERVIEW)
        val Movieratingdata  = intent?.extras?.getString(MOVIE_RATING)

        Movieoverview.text =Movieoverviewdata
        Movietitle.text = Movietitledata
        Movierelease.text = Moviereleasedata



        Glide.with(Movieposter).load(IMAGE_BASE + Movieposterdata).into(Movieposter)
        Glide.with(Moviebackdrop).load(IMAGE_BASE + Moviebackdropdata).into(Moviebackdrop)
        Movierating.rating = Movieratingdata?.toFloat()!!/2





    }



}



