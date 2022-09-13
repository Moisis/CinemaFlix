package com.example.cinemaflix.models

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
data class MovieDetailResponse(
    @SerializedName("object")
    val movedetail : MovieDetail

) : Parcelable {
    constructor() : this(MovieDetail("","","","",""))
}