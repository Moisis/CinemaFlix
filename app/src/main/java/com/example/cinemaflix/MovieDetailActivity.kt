package com.example.cinemaflix



import android.os.Bundle
import android.view.View
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.bumptech.glide.Glide
import com.example.cinemaflix.adapters.ActorAdapter
import com.example.cinemaflix.api.ApiInterface
import com.example.cinemaflix.api.MovieApiService
import com.example.cinemaflix.models.Actor
import com.example.cinemaflix.models.ActorListResponse
import com.example.cinemaflix.models.Movie
import com.google.gson.Gson
import kotlinx.android.synthetic.main.activity_movie_detail.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


const val MOVIE_BACKDROP = "extra_movie_backdrop"
const val MOVIE_POSTER = "extra_movie_poster"
const val MOVIE_TITLE = "extra_movie_title"
const val MOVIE_RATING = "extra_movie_rating"
const val MOVIE_RELEASE_DATE = "extra_movie_release_date"
const val MOVIE_OVERVIEW = "extra_movie_overview"
const val MOVIE_ID = "extra_movie_ID"
const val MOVIE_checked = "extra_movie_checked"


class MovieDetailActivity : AppCompatActivity() {
companion object{
    var favmovies : MutableSet<Movie> = mutableSetOf()
}
    private val IMAGE_BASE = "https://image.tmdb.org/t/p/w500/"
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_movie_detail)


        val Movieposter: ImageView = findViewById(R.id.movie_poster)
        val Moviebackdrop: ImageView = findViewById(R.id.movie_backdrop)
        val Movietitle: TextView = findViewById(R.id.movie_title)
        val Movierelease: TextView = findViewById(R.id.movie_release_date)
        val Movieoverview: TextView = findViewById(R.id.movie_overview)
        val Movierating: RatingBar = findViewById(R.id.movie_rating)
        val favbutton: CheckBox = findViewById(R.id.checkBoxfav)


        val Movieposterdata = intent?.extras?.getString(MOVIE_POSTER)
        val Moviebackdropdata = intent?.extras?.getString(MOVIE_BACKDROP)
        val Movietitledata = intent?.extras?.getString(MOVIE_TITLE)
        val Moviereleasedata = intent?.extras?.getString(MOVIE_RELEASE_DATE)
        val Movieoverviewdata = intent?.extras?.getString(MOVIE_OVERVIEW)
        val Movieratingdata = intent?.extras?.getString(MOVIE_RATING)
        val Moviecheckeddata = intent?.extras?.getString(MOVIE_checked).toBoolean()


        Movieoverview.text = Movieoverviewdata
        Movietitle.text = Movietitledata
        Movierelease.text = Moviereleasedata

        favbutton.isChecked = Moviecheckeddata

        val MovieIddata = intent?.extras?.getString(MOVIE_ID)?.toInt()
        val MovieIddata1 = intent?.extras?.getString(MOVIE_ID)

        Glide.with(Movieposter).load(IMAGE_BASE + Movieposterdata).into(Movieposter)
        Glide.with(Moviebackdrop).load(IMAGE_BASE + Moviebackdropdata).into(Moviebackdrop)
        Movierating.rating = Movieratingdata?.toFloat()!! / 2

        rv_actors_list.layoutManager =
            LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)
        rv_actors_list.setHasFixedSize(true)

        if (MovieIddata != null) {
            getactorlist1(MovieIddata) { actors: List<Actor> ->
                rv_actors_list.adapter = ActorAdapter(actors)
                progressBar3.visibility = View.GONE
            }
        }

        for (it in favmovies) {
          if (it.id.equals(MovieIddata1)){
              favbutton.isChecked=true
              break
          }else{
              continue
          }
        }

        favbutton.setOnCheckedChangeListener { buttonView, isChecked ->
            if (isChecked) {
                Toast.makeText(this@MovieDetailActivity, "Item added", Toast.LENGTH_SHORT).show()
                val movie1 :Movie = Movie()
                  movie1.title=Movietitledata
                movie1.id= MovieIddata1
                movie1.vote_average=Movieratingdata
                movie1.backdropPath= Moviebackdropdata
                movie1.overview=Movieoverviewdata
                movie1.poster=Movieposterdata
                movie1.release = Moviereleasedata
                movie1.checked = true
                favmovies.add(movie1)
                saveFavorite(favmovies)
            } else {
                for (it in favmovies) {
                    if (it.id.equals(MovieIddata1)){
                        favmovies.remove(it)
                        break
                    }else{
                        continue
                    }
                }
                Toast.makeText(this@MovieDetailActivity, "Item removed", Toast.LENGTH_SHORT).show()
            }
        }

    }
    private fun saveFavorite(favmovies: MutableSet<Movie>) {
        val sharedPreferences = getSharedPreferences("shared preferences", MODE_PRIVATE)
        val editor = sharedPreferences.edit()
        val gson = Gson()
        val json = gson.toJson(favmovies.toTypedArray())
        editor.putString("fav list", json)
        editor.apply()
    }





}




private fun getactorlist1(movieid :Int,callback: (List<Actor>) -> Unit) {
    val apiService = MovieApiService.getInstance().create(ApiInterface::class.java)
    apiService.getActorlist(movieid).enqueue(object : Callback<ActorListResponse> {
        override fun onResponse(call: Call<ActorListResponse>, response: Response<ActorListResponse>) {
            return callback(response.body()!!.actors)
        }

        override fun onFailure(call: Call<ActorListResponse>, t: Throwable) {





        }

    })
}






