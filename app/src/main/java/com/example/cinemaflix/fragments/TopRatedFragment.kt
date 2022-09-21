package com.example.cinemaflix.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.cinemaflix.R
import com.example.cinemaflix.adapters.MovieAdapter
import com.example.cinemaflix.adapters.MovieAdapter1
import com.example.cinemaflix.adapters.MovieAdapter2
import com.example.cinemaflix.api.ApiInterface
import com.example.cinemaflix.api.MovieApiService
import com.example.cinemaflix.models.Movie
import com.example.cinemaflix.models.MovieResponse
import kotlinx.android.synthetic.main.fragment_popular.*
import kotlinx.android.synthetic.main.fragment_top_rated.*
import kotlinx.android.synthetic.main.fragment_top_rated.progressBar2
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class TopRatedFragment() : Fragment() {

    val error : Fragment = ErrorFragment()
  private  var pageid1 = 0

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_top_rated, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        addtoprated()

        var pastVisiblesItems: Int
        var visibleItemCount: Int
        var totalItemCount: Int
        val mLayoutManager: LinearLayoutManager = LinearLayoutManager(this.activity)
        toprated_movies_list.layoutManager = mLayoutManager
        toprated_movies_list.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                if (dy > 0) { //check for scroll down
                    visibleItemCount = mLayoutManager.childCount
                    totalItemCount = mLayoutManager.itemCount
                    pastVisiblesItems = mLayoutManager.findFirstVisibleItemPosition()
                    if (visibleItemCount + pastVisiblesItems >= totalItemCount/2) {

                        addtoprated()



                    }
                }
            }
        })


    }
    fun addtoprated(){

            pageid1++
            getMovieData(pageid1) { movies: List<Movie> ->
                toprated_movies_list?.adapter = MovieAdapter1(movies)
            }

    }

    private fun getMovieData(pageid: Int, callback: (List<Movie>) -> Unit) {
        val apiService = MovieApiService.getInstance().create(ApiInterface::class.java)
        apiService.getMovieList2(pageid).enqueue(object : Callback<MovieResponse> {
            override fun onFailure(call: Call<MovieResponse>, t: Throwable) {
                val transaction = parentFragmentManager.beginTransaction()
                transaction.replace(R.id.fragmentholder2, error)
                transaction.commit()

            }

            override fun onResponse(call: Call<MovieResponse>, response: Response<MovieResponse>) {
                progressBar2.visibility = View.GONE
                return callback(response.body()!!.movies)
            }

        })
    }
}