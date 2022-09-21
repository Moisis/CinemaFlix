package com.example.cinemaflix.adapters



import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.cinemaflix.*
import com.example.cinemaflix.adapters.MovieAdapter.Companion.mutableList2
import com.example.cinemaflix.models.Movie
import kotlinx.android.synthetic.main.movie_item.view.*
import kotlinx.android.synthetic.main.movie_item.view.movie_poster
import kotlinx.android.synthetic.main.movie_item.view.movie_release_date
import kotlinx.android.synthetic.main.movie_item.view.movie_title


class MovieAdapter1(
    movies : List<Movie>
) : RecyclerView.Adapter<MovieAdapter1.MovieViewHolder>(){

init {
    mutableList2.addAll( movies)

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

    override fun getItemCount(): Int  = mutableList2.size

    override fun onBindViewHolder(holder: MovieViewHolder, position: Int) {

        holder.bindMovie(mutableList2[position])
        holder.itemView.setOnClickListener{
            val context = holder.itemView.context
            val intent =  Intent(context, MovieDetailActivity::class.java)
            intent.putExtra(MOVIE_BACKDROP, mutableList2[position].backdropPath)
            intent.putExtra(MOVIE_POSTER,mutableList2[position].poster)
            intent.putExtra(MOVIE_TITLE, mutableList2[position].title)
            intent.putExtra(MOVIE_RATING, mutableList2[position].vote_average)
            intent.putExtra(MOVIE_RELEASE_DATE, mutableList2[position].release)
            intent.putExtra(MOVIE_OVERVIEW, mutableList2[position].overview)
            intent.putExtra(MOVIE_ID,mutableList2[position].id)
            context.startActivity(intent)
        }
    }
}




