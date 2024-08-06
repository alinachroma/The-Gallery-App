package com.example.gallery.ui

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Tab
import androidx.compose.material3.TabRow
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.gallery.R
import com.example.gallery.ui.items.GalleryTopAppBar
import com.example.gallery.ui.screens.FavouritesScreen
import com.example.gallery.ui.screens.GalleryScreen
import com.example.gallery.utils.appTabs

@Composable
fun GalleryApp(
    modifier: Modifier = Modifier
) {
    val galleryViewModel: GalleryViewModel =
        viewModel(factory = GalleryViewModel.Factory)
    val favoritesViewModel: FavouritesViewModel =
        viewModel(factory = FavouritesViewModel.Factory)
    val galleryUiState by galleryViewModel.galleryUiState.collectAsState()
    val favoritesUiState by favoritesViewModel.favouritesUiState.collectAsState()

    Scaffold(
        topBar = { GalleryTopAppBar() },
        content = { contentPadding ->
            Tabs(
                tabs = appTabs,
                modifier = modifier.padding(contentPadding),
                content = listOf(
                    {
                        Surface(
                            modifier = modifier.fillMaxSize()
                        ) {
                            GalleryScreen(
                                galleryUiState = galleryUiState,
                                onFavoriteClick = { galleryImage, isFavorite ->
                                    if (isFavorite) {
                                        galleryViewModel.addFavorite(galleryImage)
                                    } else {
                                        galleryViewModel.removeFavorite(galleryImage)
                                    }
                                },
                                retryAction = galleryViewModel::getGalleryImages
                            )
                        }
                    },
                    {
                        Surface(
                            modifier = modifier.fillMaxSize()
                        ) {
                            FavouritesScreen(
                                favouritesUiState = favoritesUiState,
                                onRemoveFavouriteClick = { favoritesViewModel.removeFavorite(it) },
                                navigateToExpandedImage = { favoritesViewModel.selectFavoriteImage(it) },
                                onDismissFavorite = {
                                    favoritesViewModel.unSelectFavoriteImage()
                                }
                            )
                        }
                    }
                )
            )
        }



    )
}

@Composable
fun Tabs(
    tabs: List<Int>,
    content: List<@Composable () -> Unit>,
    modifier: Modifier = Modifier,
) {
    var selectedTab by remember { mutableIntStateOf(0) }

    Column(modifier = modifier.fillMaxSize()) {
        TabRow(
            selectedTabIndex = selectedTab
        ) {
            tabs.forEachIndexed { index, title ->
                Tab(
                    modifier = Modifier.padding(
                        top = dimensionResource(id = R.dimen.padding_medium),
                        bottom = dimensionResource(id = R.dimen.padding_medium)
                    ),
                    selected = selectedTab == index,
                    onClick = { selectedTab = index }
                ) {
                    Text(
                        text = stringResource(id = title),
                        style = MaterialTheme.typography.bodyLarge
                    )
                }
            }
        }
        content.getOrNull(selectedTab)?.invoke()
    }
}