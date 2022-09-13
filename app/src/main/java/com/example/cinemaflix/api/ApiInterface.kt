package com.example.cinemaflix.api


import com.example.cinemaflix.models.MovieResponse
import retrofit2.Call
import retrofit2.http.GET

interface ApiInterface {
    @GET("/3/movie/popular?api_key=93ff0b98eb860e0889b82357463a0ea5")
    fun getMovieList(): Call<MovieResponse>


    @GET("/3/movie/top_rated?api_key=93ff0b98eb860e0889b82357463a0ea5")
    fun getMovieList2(): Call<MovieResponse>

    @GET("/3/movie/now_playing?api_key=93ff0b98eb860e0889b82357463a0ea5")
    fun getMovieList3(): Call<MovieResponse>





}