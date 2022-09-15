package com.example.cinemaflix.fragments



import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.cinemaflix.R
import com.example.cinemaflix.adapters.MovieAdapter
import com.example.cinemaflix.api.ApiInterface
import com.example.cinemaflix.api.MovieApiService
import com.example.cinemaflix.models.Movie
import com.example.cinemaflix.models.MovieResponse
import kotlinx.android.synthetic.main.fragment_home.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class HomeFragment : Fragment() {

    val error : Fragment = ErrorFragment()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
         return inflater.inflate(com.example.cinemaflix.R.layout.fragment_home, container, false)

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        val textview1 : TextView = view.findViewById(R.id.popular1)
        val textview2 : TextView = view.findViewById(R.id.toprated)
        val textview3 : TextView = view.findViewById(R.id.Upcoming)

        textview1.setTextColor(resources.getColor(R.color.logo))

        rv_movies_list.layoutManager  = LinearLayoutManager(context)
        rv_movies_list.setHasFixedSize(true)
        getMovieData { movies: List<Movie> ->
            rv_movies_list.adapter = MovieAdapter(movies)
        }



        textview1.setOnClickListener {
            textview1.setTextColor(resources.getColor(R.color.logo))
            textview2.setTextColor(resources.getColor(R.color.secondarywhite))
            textview3.setTextColor(resources.getColor(R.color.secondarywhite))
            rv_movies_list.layoutManager = LinearLayoutManager(this.activity)
            rv_movies_list.setHasFixedSize(true)
            getMovieData { movies: List<Movie> ->
                rv_movies_list.adapter = MovieAdapter(movies)
            }
        }
        textview2.setOnClickListener {
            textview1.setTextColor(resources.getColor(R.color.secondarywhite))
            textview2.setTextColor(resources.getColor(R.color.logo))
            textview3.setTextColor(resources.getColor(R.color.secondarywhite))

            rv_movies_list.layoutManager = LinearLayoutManager(this.activity)
            rv_movies_list.setHasFixedSize(true)
            getMovieData2 { movies: List<Movie> ->
                rv_movies_list.adapter = MovieAdapter(movies)
            }
        }
        textview3.setOnClickListener {
            textview1.setTextColor(resources.getColor(R.color.secondarywhite))
            textview2.setTextColor(resources.getColor(R.color.secondarywhite))
            textview3.setTextColor(resources.getColor(R.color.logo))
            rv_movies_list.layoutManager = LinearLayoutManager(this.activity)
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
                val transaction = parentFragmentManager.beginTransaction()
                transaction.replace(R.id.fragmentholder, error)
                transaction.commit()

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
                val error : Fragment = ErrorFragment()
                val transaction = parentFragmentManager.beginTransaction()
                transaction.replace(R.id.fragmentholder, error)
                transaction.commit()
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

                val transaction = parentFragmentManager.beginTransaction()
                transaction.replace(R.id.fragmentholder, error)
                transaction.commit()
            }

            override fun onResponse(call: Call<MovieResponse>, response: Response<MovieResponse>) {

                return callback(response.body()!!.movies)
            }

        })
    }



}