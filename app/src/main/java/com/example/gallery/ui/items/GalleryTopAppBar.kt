package com.example.gallery.ui.items

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import com.example.gallery.ui.theme.GalleryTheme
import com.example.gallery.utils.ThemePreviews
import com.example.gallery.R

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun GalleryTopAppBar(modifier: Modifier = Modifier) {
    CenterAlignedTopAppBar(
        title = {
            Row(
                verticalAlignment = Alignment.CenterVertically
            ) {
                Image(
                    modifier = modifier
                        .size(dimensionResource(id = R.dimen.logo_size))
                        .padding(
                            dimensionResource(id = R.dimen.padding_small)
                        ),
                    painter = painterResource(R.drawable.gallery_logo),
                    contentDescription = null
                )
                Spacer(
                    modifier = Modifier
                        .size(dimensionResource(id = R.dimen.padding_small))
                )
                Text(
                    text = stringResource(R.string.app_name),
                    style = MaterialTheme.typography.bodyLarge,
                    fontWeight = FontWeight.Bold
                )
            }
        },
        modifier = modifier
    )
}

@ThemePreviews
@Composable
fun GalleryTopAppBarPreview() {
    GalleryTheme {
        GalleryTopAppBar()
    }
}