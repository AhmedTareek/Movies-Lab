package com.watch.movieslab

import android.os.Parcel
import android.os.Parcelable
import com.google.gson.annotations.SerializedName

data class MovieDetails (
   @SerializedName("release_date") val releaseDate:String?,
   @SerializedName("original_title") val originalTitle:String?,
   @SerializedName("vote_average") val rating:Float,
   @SerializedName("poster_path") val poster:String?
   ) : Parcelable {
   constructor(parcel: Parcel) : this(
      parcel.readString(),
      parcel.readString(),
      parcel.readFloat(),
      parcel.readString()
   ) {
   }

   override fun writeToParcel(parcel: Parcel, flags: Int) {
      parcel.writeString(releaseDate)
      parcel.writeString(originalTitle)
      parcel.writeFloat(rating)
      parcel.writeString(poster)
   }

   override fun describeContents(): Int {
      return 0
   }

   companion object CREATOR : Parcelable.Creator<MovieDetails> {
      override fun createFromParcel(parcel: Parcel): MovieDetails {
         return MovieDetails(parcel)
      }

      override fun newArray(size: Int): Array<MovieDetails?> {
         return arrayOfNulls(size)
      }
   }

}