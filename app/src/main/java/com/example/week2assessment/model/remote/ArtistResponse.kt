package com.example.week2assessment.model.remote

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class ArtistResponse(
    val results: List<ArtistItem>

): Parcelable

@Parcelize
data class ArtistItem (
    val artistName: String,
    val collectionName: String,
    val artworkUrl60: String,
    val trackPrice: Float,
    val previewUrl: String
):Parcelable
