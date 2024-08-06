package com.example.gallery.ui.items

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.example.gallery.R
import com.example.gallery.ui.theme.GalleryTheme
import com.example.gallery.utils.ThemePreviews
import com.example.gallery.utils.mockGallery

/**
 * Composable for a single gallery image.
 */

@Composable
fun ImageItem(
    imageUrl: String,
    modifier: Modifier = Modifier
) {
    AsyncImage(
        model = ImageRequest.Builder(context = LocalContext.current)
            .data(imageUrl)
            .crossfade(true)
            .build(),
        error = painterResource(R.drawable.ic_connection_error),
        placeholder = painterResource(R.drawable.ic_progress_activity),
        contentDescription = stringResource(R.string.gallery_image),
        contentScale = ContentScale.Fit,
        modifier = modifier.fillMaxSize()
    )
}

@ThemePreviews
@Composable
fun ImageItemPreview() {
    GalleryTheme {
        ImageItem(
            imageUrl = mockGallery[0].url
        )
    }
}
