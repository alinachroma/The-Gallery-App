package com.example.gallery.ui.buttons

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.material3.Icon
import androidx.compose.material3.IconToggleButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.graphicsLayer
import com.example.gallery.ui.theme.GalleryTheme
import com.example.gallery.utils.ThemePreviews

@Composable
fun FavouriteButton(
    modifier: Modifier = Modifier,
    hasFavorite: Boolean,
    onFavoriteClick: (Boolean) -> Unit,
) {
    var isFavorite by remember { mutableStateOf(hasFavorite) }
    IconToggleButton(
        checked = isFavorite,
        onCheckedChange = {
            isFavorite = !isFavorite
            onFavoriteClick(isFavorite)
        }
    ) {
        Icon(
            tint = MaterialTheme.colorScheme.tertiary,
            modifier = modifier.graphicsLayer {
                scaleX = 1.7f
                scaleY = 1.7f
            },
            imageVector = if (isFavorite) {
                Icons.Filled.Favorite
            } else {
                Icons.Default.FavoriteBorder
            },
            contentDescription = null
        )
    }
}

@ThemePreviews
@Composable
fun FavouriteButtonPreview() {
    GalleryTheme {
        FavouriteButton(
            onFavoriteClick = {},
            hasFavorite = true
        )
    }
}