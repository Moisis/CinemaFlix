package com.example.cinemaflix.api

import com.example.cinemaflix.models.MovieResponse
import retrofit2.Call
import retrofit2.http.GET

interface ApiInterface {
    @GET("/3/movie/popular?api_key=bbf5a3000e95f1dddf266b5e187d4b21")
    fun getMovieList(): Call<MovieResponse>
}