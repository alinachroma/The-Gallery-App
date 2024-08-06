package com.example.gallery.data

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.gallery.model.FavouriteImage

/**
 * Database class with a singleton Instance object.
 * Necessary to persist favourite images.
 */
@Database(entities = [FavouriteImage::class], version = 1, exportSchema = false)
abstract class FavouriteImagesDatabase: RoomDatabase() {
    abstract fun favouriteImagesDao(): FavouriteImagesDao

    companion object {
        @Volatile
        private var Instance: FavouriteImagesDatabase? = null

        fun getDatabase(context: Context): FavouriteImagesDatabase {
            return Instance ?: synchronized(this) {
                Room.databaseBuilder(context, FavouriteImagesDatabase::class.java, "images_database")
                    .build()
                    .also { Instance = it }
            }
        }
    }
}
