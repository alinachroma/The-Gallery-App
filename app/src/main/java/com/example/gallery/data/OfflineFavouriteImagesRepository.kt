package com.example.gallery.data

import com.example.gallery.model.FavouriteImage
import kotlinx.coroutines.flow.Flow

class OfflineFavouriteImagesRepository(
    private val favoriteImagesDao: FavouriteImagesDao
) : FavouriteImagesRepository {
    override fun getAllFavoritesStream(): Flow<List<FavouriteImage>> =
        favoriteImagesDao.getAllFavouriteImages()

    override suspend fun insertFavoriteImage(favoriteImage: FavouriteImage) =
        favoriteImagesDao.insert(favouriteImage = favoriteImage)

    override suspend fun deleteFavoriteImage(favoriteImage: FavouriteImage) {
        favoriteImagesDao.delete(favouriteImage = favoriteImage)
    }
    override fun getFavoriteIds(): Flow<List<Int>> {
        return favoriteImagesDao.getAllFavouriteIds()
    }
}