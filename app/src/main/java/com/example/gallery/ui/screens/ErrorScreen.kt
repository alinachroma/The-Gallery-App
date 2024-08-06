package com.example.gallery.ui.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import com.example.gallery.R
import com.example.gallery.ui.theme.GalleryTheme
import com.example.gallery.utils.ThemePreviews

/**
 * The result (home) screen when the gallery is in the Error state.
 */
@Composable
fun ErrorScreen(
    modifier: Modifier = Modifier,
    retryAction: () -> Unit
) {
    Column(
        modifier = modifier,
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Image(
            painter = painterResource(
                id = R.drawable.ic_connection_error
            ),
            contentDescription = ""
        )
        Text(
            text = stringResource(R.string.loading_failed),
            modifier = Modifier.padding(
                dimensionResource(id = R.dimen.padding_medium)
            )
        )
        Button(onClick = retryAction) {
            Text(stringResource(R.string.retry))
        }
    }
}

@ThemePreviews
@Composable
fun ErrorScreenPreview() {
    GalleryTheme {
        ErrorScreen(retryAction = {})
    }
}