package com.example.gallery.ui.screens

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import com.example.gallery.model.GalleryImage
import com.example.gallery.ui.GalleryUiState

@Composable
fun GalleryScreen(
    modifier: Modifier = Modifier,
    galleryUiState: GalleryUiState,
    onFavoriteClick: (GalleryImage, Boolean) -> Unit,
    retryAction: () -> Unit,
) {
    var selectedImageId by rememberSaveable {
        mutableStateOf<Int?>(null)
    }
    when (galleryUiState) {
        is GalleryUiState.Loading -> LoadingScreen(modifier = modifier.fillMaxSize())
        is GalleryUiState.Success -> {
            ResultScreen(
                galleryUiState.images,
                navigateToExpandedImage = { selectedImageId = it },
                modifier = modifier.fillMaxWidth(),
            )
            if (selectedImageId != null) {
                GalleryExtendedImage(
                    image = galleryUiState.images.first { it.id == selectedImageId },
                    onDismiss = { selectedImageId = null },
                    onfavoriteClick = onFavoriteClick,
                )
            }
        }

        is GalleryUiState.Error -> ErrorScreen(
            retryAction = retryAction,
            modifier = modifier.fillMaxSize()
        )
    }
}