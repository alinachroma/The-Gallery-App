package com.example.gallery.data

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.gallery.model.FavouriteImage
import kotlinx.coroutines.flow.Flow

@Dao
interface FavouriteImagesDao {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insert(favouriteImage: FavouriteImage)

    @Delete
    suspend fun delete(favouriteImage: FavouriteImage)

    @Query("SELECT * from favourites")
    fun getAllFavouriteImages() : Flow<List<FavouriteImage>>

    @Query("SELECT id from favourites")
    fun getAllFavouriteIds() : Flow<List<Int>>
}