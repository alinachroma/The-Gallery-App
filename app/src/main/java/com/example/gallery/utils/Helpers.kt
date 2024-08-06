package com.example.gallery.utils

import com.example.gallery.R
import com.example.gallery.model.GalleryImage

/**
 * List of titles for the Gallery and Favourites tabs.
 * Placed there to make the [GalleryApp.kt] easier to read.
 */
val appTabs = listOf(
    R.string.app_name, R.string.favourites
)

/**
 * Mock data for the Compose Previews.
 */
val mockGallery = listOf(
    GalleryImage(
        albumId = 1,
        id = 1,
        title = "title",
        url = "https://via.placeholder.com/600/92c952",
        thumbnailUrl = "https://via.placeholder.com/150/92c952"
    ),
    GalleryImage(
        albumId = 1,
        id = 2,
        title = "title",
        url = "https://via.placeholder.com/600/771796",
        thumbnailUrl = "https://via.placeholder.com/150/771796"
    ),
    GalleryImage(
        albumId = 1,
        id = 3,
        title = "title",
        url = "https://via.placeholder.com/600/24f355",
        thumbnailUrl = "https://via.placeholder.com/150/24f355"
    )
)