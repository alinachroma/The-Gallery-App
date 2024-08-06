package com.example.gallery.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.example.gallery.R
import com.example.gallery.model.GalleryImage
import com.example.gallery.ui.buttons.FavouriteButton
import com.example.gallery.ui.items.ImageItem
import com.example.gallery.ui.theme.GalleryTheme
import com.example.gallery.utils.ThemePreviews
import com.example.gallery.utils.mockGallery

/**
 * The first tab that contains an image gallery.
 * A screen for the Success state, when images are successfully fetched.
 */

@Composable
fun ResultScreen(
    images: List<GalleryImage>,
    navigateToExpandedImage: (Int) -> Unit = { },
    modifier: Modifier = Modifier
) {
    LazyVerticalGrid(
        columns = GridCells.Adaptive(150.dp),
        modifier = modifier.padding(
            dimensionResource(id = R.dimen.padding_small)
        ),
    ) {
        items(items = images, key = { image -> image.id }) { image ->
            ImageItem(imageUrl = image.thumbnailUrl,
                modifier = modifier
                    .padding(dimensionResource(id = R.dimen.padding_small))
                    .fillMaxWidth()
                    .aspectRatio(1f)
                    .clickable {
                        navigateToExpandedImage(image.id)
                    }
            )
        }
    }
}

@Composable
fun GalleryExtendedImage(
    image: GalleryImage,
    onDismiss: () -> Unit,
    onfavouriteClick: (GalleryImage, Boolean) -> Unit,
    modifier: Modifier = Modifier,
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
            hasFavourite = image.isFavourite,
            onFavouriteClick = {
                onfavouriteClick(image, it)
            }
        )
    }
}

@Composable
fun Scrim(
    onClose: () -> Unit,
    modifier: Modifier = Modifier,
) {
    Box(
        modifier = modifier
            .fillMaxSize()
            .pointerInput(onClose) {
                detectTapGestures { onClose() }
            }
            .background(
                Color.DarkGray.copy(alpha = 0.75f)
            )
    )
}

@ThemePreviews
@Composable
fun GalleryScreenPreview() {
    GalleryTheme {
        ResultScreen(
            images = mockGallery
        )
    }
}

@ThemePreviews
@Composable
fun ExtendedImagePreview() {
    GalleryTheme {
        GalleryExtendedImage(
            image = mockGallery[0],
            onDismiss = {},
            onfavouriteClick = { image, isFavourite -> }
        )
    }
}