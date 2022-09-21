package com.example.cinemaflix.api


import com.example.cinemaflix.models.ActorListResponse
import com.example.cinemaflix.models.MovieResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface ApiInterface {
    @GET("/3/movie/popular?api_key=93ff0b98eb860e0889b82357463a0ea5")
    fun getMovieList(@Query("page") page : Int): Call<MovieResponse>


    @GET("/3/movie/top_rated?api_key=93ff0b98eb860e0889b82357463a0ea5")
    fun getMovieList2(@Query("page") page : Int): Call<MovieResponse>

    @GET("/3/movie/now_playing?api_key=93ff0b98eb860e0889b82357463a0ea5")
    fun getMovieList3(@Query("page") page : Int): Call<MovieResponse>

    @GET("/3/movie/{movie_id}/credits?api_key=93ff0b98eb860e0889b82357463a0ea5")
    fun getActorlist(@Path("movie_id") MovieId: Int): Call<ActorListResponse>

    @GET("/3/search/movie?api_key=93ff0b98eb860e0889b82357463a0ea5")
    fun getSearches(@Query("query") query : String): Call<MovieResponse>


}