package com.example.gallery

import android.app.Application
import com.example.gallery.data.AppContainer
import com.example.gallery.data.DefaultAppContainer

/**
 * This class is used across the app to obtain dependencies.
 */
class GalleryImagesApplication : Application() {
    lateinit var container: AppContainer
    override fun onCreate() {
        super.onCreate()
        container = DefaultAppContainer(this)
    }
}