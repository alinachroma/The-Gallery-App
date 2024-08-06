package com.example.gallery.data

import com.example.gallery.model.GalleryImage
import com.example.gallery.network.GalleryApiService

interface GalleryImagesRepository {
    suspend fun getImages(): List<GalleryImage>
}

class NetworkGalleryImagesRepository(
    private val galleryApiService: GalleryApiService
) : GalleryImagesRepository {
    override suspend fun getImages(): List<GalleryImage> = galleryApiService.getImages()
}