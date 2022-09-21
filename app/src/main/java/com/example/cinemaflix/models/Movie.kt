package com.example.cinemaflix.models

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Movie(

    @SerializedName("id")
var id : String ?,

    @SerializedName("title")
var title : String?,

    @SerializedName("poster_path")
var poster : String?,

    @SerializedName("release_date")
var release : String?,

    @SerializedName("vote_average")
var vote_average : String?,


    @SerializedName("overview")
var overview: String?,

    @SerializedName("backdrop_path")
var backdropPath: String?,

    var checked : Boolean

) : Parcelable {
    constructor() :this("","","","","","","",false)

}