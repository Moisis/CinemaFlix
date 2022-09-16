package com.example.cinemaflix.models

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize




@Parcelize
data class Actor(

    @SerializedName("id")
    val id : String ?,

    @SerializedName("name")
    val name : String ?,

    @SerializedName("character")
    val character : String ?,

    @SerializedName("profile_path")
    val profile_path : String ?,

    @SerializedName("known_for_department")
    val known_for_department : String ?,

    @SerializedName("biography")
    val biography : String ?,

    @SerializedName("birthday")
    val birthday : String ?,


    ) : Parcelable {
    constructor():this("","","","","","","")
}