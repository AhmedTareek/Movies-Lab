package com.watch.movieslab

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
data class MovieDetails (
   @SerializedName("release_date") val releaseDate:String?,
   @SerializedName("original_title") val originalTitle:String?,
   @SerializedName("vote_average") val rating:Float,
   @SerializedName("poster_path") val poster:String?
   )  : Parcelable

