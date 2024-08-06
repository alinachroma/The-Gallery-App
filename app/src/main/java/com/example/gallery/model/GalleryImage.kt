package com.example.gallery.model

import kotlinx.serialization.Serializable

@Serializable
data class GalleryImage (
    val albumId: Int,
    val id: Int,
    val title: String,
    val url: String,
    val thumbnailUrl: String,
    var isFavorite: Boolean = false
) {
    /**
     * Extension function to convert GalleryImage to FavoriteImage.
     */
    fun toFavouriteImage(): FavouriteImage = FavouriteImage(
        id = id,
        url = url,
        thumbnailUrl = thumbnailUrl
    )
}
