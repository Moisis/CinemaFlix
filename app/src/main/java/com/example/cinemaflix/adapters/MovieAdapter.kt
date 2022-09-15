package com.example.cinemaflix.adapters

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.cinemaflix.*
import com.example.cinemaflix.models.Movie
import kotlinx.android.synthetic.main.movie_item.view.*
import kotlin.math.round

class MovieAdapter(
private val movies : List<Movie>
) : RecyclerView.Adapter<MovieAdapter.MovieViewHolder>(){

    class MovieViewHolder(view : View) : RecyclerView.ViewHolder(view){
        private val IMAGE_BASE = "https://image.tmdb.org/t/p/original/"
        fun bindMovie(movie : Movie){
            itemView.movie_title.text = movie.title
            itemView.movie_release_date.text = movie.release
            itemView.rating.text = String.format("%.1f",(movie.vote_average?.toFloat()!!/2))
            //itemView.rating.text = (movie.vote_average?.toFloat()!!/2).toString()
            Glide.with(itemView).load(IMAGE_BASE + movie.poster).into(itemView.movie_poster)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieViewHolder {
        return MovieViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.movie_item, parent, false)
        )
    }

    override fun getItemCount(): Int = movies.size

    override fun onBindViewHolder(holder: MovieViewHolder, position: Int) {
        holder.bindMovie(movies[position])
        holder.itemView.setOnClickListener{
            val context = holder.itemView.context
            val intent =  Intent(context, MovieDetailActivity::class.java)
            intent.putExtra(MOVIE_BACKDROP, movies[position].backdropPath)
            intent.putExtra(MOVIE_POSTER, movies[position].poster)
            intent.putExtra(MOVIE_TITLE, movies[position].title)
            intent.putExtra(MOVIE_RATING, movies[position].vote_average)
            intent.putExtra(MOVIE_RELEASE_DATE, movies[position].release)
            intent.putExtra(MOVIE_OVERVIEW, movies[position].overview)
            context.startActivity(intent)
        }
    }
}