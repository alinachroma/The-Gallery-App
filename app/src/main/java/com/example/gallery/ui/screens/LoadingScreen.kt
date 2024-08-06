package com.example.gallery.ui.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.example.gallery.R
import com.example.gallery.ui.theme.GalleryTheme
import com.example.gallery.utils.ThemePreviews

/**
 * The result (home) screen when the gallery is in the Loading state.
 */
@Composable
fun LoadingScreen(modifier: Modifier = Modifier) {
    Image(
        modifier = modifier.size(200.dp),
        painter = painterResource(R.drawable.ic_progress_activity),
        contentDescription = stringResource(R.string.loading)
    )
}

@ThemePreviews
@Composable
fun LoadingScreenPreview() {
    GalleryTheme {
        LoadingScreen()
    }
}