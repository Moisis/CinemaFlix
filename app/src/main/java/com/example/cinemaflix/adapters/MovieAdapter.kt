package com.example.cinemaflix.adapters


import android.app.Activity
import android.content.Context
import android.content.Intent
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.Glide.init
import com.example.cinemaflix.*
import com.example.cinemaflix.fragments.PopularFragment
import com.example.cinemaflix.models.Movie
import kotlinx.android.synthetic.main.movie_item.view.*
import kotlinx.android.synthetic.main.movie_item.view.movie_poster
import kotlinx.android.synthetic.main.movie_item.view.movie_release_date
import kotlinx.android.synthetic.main.movie_item.view.movie_title
import kotlin.math.log


class MovieAdapter(
    private var movies : List<Movie>
) : RecyclerView.Adapter<MovieAdapter.MovieViewHolder>(){
    companion object {
        val mutableList: MutableList<Movie> = mutableListOf()
        val mutableList2: MutableList<Movie> = mutableListOf()
        val mutableList3: MutableList<Movie> = mutableListOf()
    }
init {
    mutableList.addAll( movies)

}


    class MovieViewHolder(view : View) : RecyclerView.ViewHolder(view){
        private val IMAGE_BASE = "https://image.tmdb.org/t/p/original/"
        fun bindMovie(movie : Movie){
            itemView.movie_title.text = movie.title
            itemView.movie_release_date.text = movie.release
            itemView.rating.text = String.format("%.1f",(movie.vote_average?.toFloat()!!/2))
            Glide.with(itemView).load(IMAGE_BASE + movie.poster).into(itemView.movie_poster)

        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieViewHolder {
        return MovieViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.movie_item, parent, false)
        )
    }

    override fun getItemCount(): Int  = mutableList.size

    override fun onBindViewHolder(holder: MovieViewHolder, position: Int) {

            holder.bindMovie(mutableList[position])
            holder.itemView.setOnClickListener{
                val context = holder.itemView.context
                val intent =  Intent(context, MovieDetailActivity::class.java)
                intent.putExtra(MOVIE_BACKDROP, mutableList[position].backdropPath)
                intent.putExtra(MOVIE_POSTER,mutableList[position].poster)
                intent.putExtra(MOVIE_TITLE, mutableList[position].title)
                intent.putExtra(MOVIE_RATING, mutableList[position].vote_average)
                intent.putExtra(MOVIE_RELEASE_DATE, mutableList[position].release)
                intent.putExtra(MOVIE_OVERVIEW, mutableList[position].overview)
                intent.putExtra(MOVIE_ID,mutableList[position].id)
                intent.putExtra(MOVIE_checked,mutableList[position].id)
                context.startActivity(intent)
            }
        }



}




