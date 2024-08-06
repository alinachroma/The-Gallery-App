package com.example.gallery.data

import com.example.gallery.model.FavouriteImage
import kotlinx.coroutines.flow.Flow

interface FavouriteImagesRepository {
    fun getAllFavouritesStream(): Flow<List<FavouriteImage>>
    suspend fun insertFavouriteImage(favouriteImage: FavouriteImage)
    suspend fun deleteFavouriteImage(favouriteImage: FavouriteImage)
    fun getFavouriteIds(): Flow<List<Int>>
}