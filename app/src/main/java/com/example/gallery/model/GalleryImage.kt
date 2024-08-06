package com.example.gallery.model

import kotlinx.serialization.Serializable

@Serializable
data class GalleryImage (
    val albumId: Int,
    val id: Int,
    val title: String,
    val url: String,
    val thumbnailUrl: String,
    var isFavourite: Boolean = false
) {
    /**
     * Extension function to convert GalleryImage to FavouriteImage.
     */
    fun toFavouriteImage(): FavouriteImage = FavouriteImage(
        id = id,
        url = url,
        thumbnailUrl = thumbnailUrl
    )
}
