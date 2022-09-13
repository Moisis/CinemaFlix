package com.example.cinemaflix

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView
import com.bumptech.glide.Glide
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.movie_item.view.*

class MovieDetail : AppCompatActivity() {
    private val IMAGE_BASE = "https://image.tmdb.org/t/p/w500/"
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_movie_detail)
        val imageView: ImageView = findViewById(R.id.movie_poster2)
        val imageviewdata = intent.extras?.getString("Poster")
        Glide.with(imageView).load(IMAGE_BASE + imageviewdata).into(imageView)
    }
}