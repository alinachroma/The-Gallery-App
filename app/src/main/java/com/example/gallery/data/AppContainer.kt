package com.example.gallery.data

import android.content.Context
import com.example.gallery.network.GalleryApiService
import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import kotlinx.serialization.json.Json
import okhttp3.MediaType.Companion.toMediaType
import retrofit2.Retrofit

/**
 * Dependency Injection container, Retrofit setup - builder, service object.
 */
interface AppContainer {
    val galleryImagesRepository: GalleryImagesRepository
    val favouriteImagesRepository: FavouriteImagesRepository
}

class DefaultAppContainer(private val context: Context) : AppContainer {
    private val baseUrl = "https://jsonplaceholder.typicode.com/"

    private val retrofit: Retrofit = Retrofit.Builder()
        .addConverterFactory(Json.asConverterFactory("application/json".toMediaType()))
        .baseUrl(baseUrl)
        .build()

    private val retrofitService: GalleryApiService by lazy {
        retrofit.create(GalleryApiService::class.java)
    }

    override val galleryImagesRepository: GalleryImagesRepository by lazy {
        NetworkGalleryImagesRepository(retrofitService)
    }

    override val favouriteImagesRepository: FavouriteImagesRepository by lazy {
        OfflineFavouriteImagesRepository(
            FavouriteImagesDatabase.getDatabase(context)
                .favouriteImagesDao()
        )
    }
}