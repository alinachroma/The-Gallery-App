package com.example.gallery.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "favourites")
data class FavouriteImage(
    @PrimaryKey
    val id: Int,
    val url: String,
    val thumbnailUrl: String
)