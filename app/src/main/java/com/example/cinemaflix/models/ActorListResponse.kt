package com.example.cinemaflix.models

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
data class ActorListResponse(

    @SerializedName("cast")
    val actors : List<Actor>




) :Parcelable{
    constructor() : this(mutableListOf())
}
