package com.example.gallery.network

import com.example.gallery.model.GalleryImage
import retrofit2.http.GET

interface GalleryApiService {
    @GET("photos")
    suspend fun getImages(): List<GalleryImage>
}