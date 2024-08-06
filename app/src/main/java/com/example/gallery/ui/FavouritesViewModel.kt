package com.example.gallery.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProvider.AndroidViewModelFactory.Companion.APPLICATION_KEY
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import com.example.gallery.GalleryImagesApplication
import com.example.gallery.data.FavouriteImagesRepository
import com.example.gallery.model.FavouriteImage
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

/**
 * UI state for the Favorites screen.
 */
data class FavouritesUiState(
    val favouriteImages: List<FavouriteImage> = emptyList(),
    val selectedImage: FavouriteImage? = null
)

class FavouritesViewModel(
    private val favouriteImagesRepository: FavouriteImagesRepository
) : ViewModel() {
    private val _favouritesUiState = MutableStateFlow(FavouritesUiState())
    val favouritesUiState = _favouritesUiState.asStateFlow()

    init {
        getFavorites()
    }

    fun getFavorites(){
        viewModelScope.launch {
            favouriteImagesRepository.getAllFavoritesStream().collect {
                    favorites -> _favouritesUiState.value = FavouritesUiState(favorites)
            }
        }
    }

    fun selectFavoriteImage(galleryImage: FavouriteImage){
        _favouritesUiState.value =
            _favouritesUiState.value.copy(selectedImage = galleryImage)
    }

    fun unSelectFavoriteImage(){
        _favouritesUiState.value =
            _favouritesUiState.value.copy(selectedImage = null)
    }

    fun removeFavorite(favoriteImage: FavouriteImage) {
        viewModelScope.launch {
            favouriteImagesRepository.deleteFavoriteImage(favoriteImage)
            unSelectFavoriteImage()
        }
    }

    companion object {
        val Factory: ViewModelProvider.Factory = viewModelFactory {
            initializer {
                val application = (this[APPLICATION_KEY] as GalleryImagesApplication)
                val favoriteImagesRepository = application.container.favouriteImagesRepository
                FavouritesViewModel(
                    favouriteImagesRepository = favoriteImagesRepository
                )
            }
        }
    }
}

