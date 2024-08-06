package com.example.gallery.ui.screens

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.example.gallery.R
import com.example.gallery.model.FavouriteImage
import com.example.gallery.ui.FavouritesUiState
import com.example.gallery.ui.buttons.FavouriteButton
import com.example.gallery.ui.items.ImageItem
import com.example.gallery.ui.theme.GalleryTheme
import com.example.gallery.utils.ThemePreviews
import com.example.gallery.utils.mockGallery

@Composable
fun FavouritesScreen(
    modifier: Modifier = Modifier,
    favouritesUiState: FavouritesUiState,
    onRemoveFavouriteClick: (FavouriteImage) -> Unit,
    navigateToExpandedImage: (FavouriteImage) -> Unit = { },
    contentPadding: PaddingValues = PaddingValues(0.dp),
    onDismissFavourite: () -> Unit
) {
    LazyColumn(
        modifier = modifier,
        verticalArrangement = Arrangement.spacedBy(
            dimensionResource(id = R.dimen.padding_medium)
        ),
        contentPadding = contentPadding,
    ) {
        items(favouritesUiState.favouriteImages) { favouriteImage ->
            ImageItem(
                imageUrl = favouriteImage.thumbnailUrl,
                modifier = modifier
                    .padding(dimensionResource(id = R.dimen.padding_small))
                    .fillMaxWidth()
                    .aspectRatio(1.5f)
                    .clickable {
                        navigateToExpandedImage(favouriteImage)
                    }
            )
        }
    }
    if (favouritesUiState.selectedImage != null) {
        FavouriteExtendedImage(
            image = favouritesUiState.selectedImage,
            onDismiss = onDismissFavourite,
            onRemoveFavouriteClick = onRemoveFavouriteClick,
        )
    }
}

@Composable
fun FavouriteExtendedImage(
    image: FavouriteImage,
    onDismiss: () -> Unit,
    onRemoveFavouriteClick: (FavouriteImage) -> Unit,
    modifier: Modifier = Modifier
) {
    Box(
        modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        Scrim(onClose = onDismiss)
        AsyncImage(
            model = ImageRequest.Builder(context = LocalContext.current)
                .data(image.url)
                .crossfade(true)
                .build(),
            error = painterResource(R.drawable.ic_connection_error),
            placeholder = painterResource(R.drawable.ic_progress_activity),
            contentDescription = stringResource(R.string.gallery_image),
            contentScale = ContentScale.Fit,
            modifier = modifier.fillMaxSize()
        )
        FavouriteButton(
            hasFavourite = true,
            onFavouriteClick = {
                onRemoveFavouriteClick(image)
            },
        )
    }
}

@ThemePreviews
@Composable
fun FavouriteImagesScreenPreview() {
    GalleryTheme {
        FavouritesScreen(
            favouritesUiState = FavouritesUiState(
                favouriteImages = mockGallery.map { it.toFavouriteImage() },
                selectedImage = null
            ),
            onRemoveFavouriteClick = {},
            navigateToExpandedImage = {},
            onDismissFavourite = {}
        )
    }
}