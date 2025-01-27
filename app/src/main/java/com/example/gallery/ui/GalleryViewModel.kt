package com.example.gallery.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProvider.AndroidViewModelFactory.Companion.APPLICATION_KEY
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import com.example.gallery.GalleryImagesApplication
import com.example.gallery.data.FavouriteImagesRepository
import com.example.gallery.data.GalleryImagesRepository
import com.example.gallery.model.GalleryImage
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import retrofit2.HttpException
import java.io.IOException

/**
 * UI state for the Gallery screen
 */
sealed interface GalleryUiState {
    data class Success(val images: List<GalleryImage>) : GalleryUiState
    object Error : GalleryUiState
    object Loading : GalleryUiState
}

class GalleryViewModel(
    private val galleryImagesRepository: GalleryImagesRepository,
    private val favouriteImagesRepository: FavouriteImagesRepository
) : ViewModel() {
    private val _galleryUiState = MutableStateFlow<GalleryUiState>(GalleryUiState.Loading)
    val galleryUiState: StateFlow<GalleryUiState> = _galleryUiState.asStateFlow()

    init {
        getGalleryImages()
    }

    fun getGalleryImages() {
        viewModelScope.launch {
            _galleryUiState.value = GalleryUiState.Loading
            try {
                favouriteImagesRepository.getFavouriteIds().collect { favourites ->
                    _galleryUiState.value =
                        GalleryUiState.Success(galleryImagesRepository.getImages()
                            .map { image ->
                                image.isFavourite = favourites.contains(image.id)
                                image
                            }
                        )
                }
            } catch (e: IOException) {
                _galleryUiState.value = GalleryUiState.Error
            } catch (e: HttpException) {
                _galleryUiState.value = GalleryUiState.Error
            }
        }
    }

    fun addFavourite(galleryImage: GalleryImage) {
        viewModelScope.launch {
            favouriteImagesRepository.insertFavouriteImage(
                galleryImage.toFavouriteImage()
            )
        }
    }

    fun removeFavourite(galleryImage: GalleryImage) {
        viewModelScope.launch {
            favouriteImagesRepository.deleteFavouriteImage(
                galleryImage.toFavouriteImage()
            )
        }
    }

    companion object {
        val Factory: ViewModelProvider.Factory = viewModelFactory {
            initializer {
                val application = (this[APPLICATION_KEY] as GalleryImagesApplication)
                val galleryImagesRepository = application.container.galleryImagesRepository
                val favouriteImagesRepository = application.container.favouriteImagesRepository
                GalleryViewModel(
                    galleryImagesRepository = galleryImagesRepository,
                    favouriteImagesRepository = favouriteImagesRepository
                )
            }
        }
    }
}