package com.example.gallery.data

import com.example.gallery.model.FavouriteImage
import kotlinx.coroutines.flow.Flow

interface FavouriteImagesRepository {
    fun getAllFavoritesStream(): Flow<List<FavouriteImage>>
    suspend fun insertFavoriteImage(favoriteImage: FavouriteImage)
    suspend fun deleteFavoriteImage(favoriteImage: FavouriteImage)
    fun getFavoriteIds(): Flow<List<Int>>
}