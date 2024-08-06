package com.example.gallery.data

import com.example.gallery.model.FavouriteImage
import kotlinx.coroutines.flow.Flow

class OfflineFavouriteImagesRepository(
    private val favouriteImagesDao: FavouriteImagesDao
) : FavouriteImagesRepository {
    override fun getAllFavouritesStream(): Flow<List<FavouriteImage>> =
        favouriteImagesDao.getAllFavouriteImages()

    override suspend fun insertFavouriteImage(favouriteImage: FavouriteImage) =
        favouriteImagesDao.insert(favouriteImage = favouriteImage)

    override suspend fun deleteFavouriteImage(favouriteImage: FavouriteImage) {
        favouriteImagesDao.delete(favouriteImage = favouriteImage)
    }
    override fun getFavouriteIds(): Flow<List<Int>> {
        return favouriteImagesDao.getAllFavouriteIds()
    }
}