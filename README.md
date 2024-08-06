# The Gallery App

An Android app that makes requests to a web service and displays a list of images from the placeholder API and gives a possibility to mark them as favourites.
Built fully with Jetpack Compose.

## Features

* The app consists of two tabs (`Gallery` and `Favourites`), the first tab displays images from the placeholder API, the second tab displays a list of images that are marked as favourite and stored locally.
* When the user clicks on an image (`Gallery` tab), the image opens in a bigger size and it is possible to dismiss it or mark it as favourite.
* As soon as the image is marked as favourite, it is stored in the local database and displayed in the `Favourites` tab. It is also possible to view a favourite image in a bigger size (by clicking on it), and to dismiss or remove it from the list of favourites.
* The app supports both Light and Dark themes.

## Screenshots
<img width="700" alt="gallery" src="https://github.com/user-attachments/assets/6d9f0287-967c-4c41-b7bf-75eca6ff533c">

## Demo
| Features | Offline behavior |
| ------------- | ------------- |
| <video src="https://github.com/user-attachments/assets/fc1f655b-dccb-4118-a6b3-75df00598642"> | <video src="https://github.com/user-attachments/assets/14007349-b1b4-4429-940c-62393a72e83a">| 

### UI State management
The [GalleryUiState](app/src/main/java/com/example/gallery/ui/GalleryViewModel.kt) defines the data to be displayed in the `Gallery` tab. 
There are three possible states that the app can handle:
* `Success` (images are fetched successfully) - UI is represented by the [ResultScreen.kt](app/src/main/java/com/example/gallery/ui/screens/ResultScreen.kt) Composable.
* `Loading` (images are in progress) - UI is represented by the [LoadingScreen.kt](app/src/main/java/com/example/gallery/ui/screens/LoadingScreen.kt) Composable.
* `Error` (images are failed to load, connection issues) - UI is represented by the [ErrorScreen.kt](app/src/main/java/com/example/gallery/ui/screens/ErrorScreen.kt) Composable.
The [GalleryScreen.kt](app/src/main/java/com/example/gallery/ui/screens/GalleryScreen.kt) Composable wraps the above-mentioned composables (for the `Success`, `Loading` or `Error` state) and displays the content accordingly.

### Architecture Components
* The `data` layer is represented by the repositories, data classes and services - [GalleryImagesRepository.kt](app/src/main/java/com/example/gallery/data/GalleryImagesRepository.kt), [GalleryApiService.kt](app/src/main/java/com/example/gallery/network/GalleryApiService.kt) - these are fetching the data from the placeholder API. The state of the request is being observed and displayed accordingly, which is defined in the [GalleryViewModel.kt](app/src/main/java/com/example/gallery/ui/GalleryViewModel.kt) with [GalleryUiState.kt](app/src/main/java/com/example/gallery/ui/GalleryViewModel.kt). [FavouriteImagesRepository](app/src/main/java/com/example/gallery/data/FavouriteImagesRepository.kt) and its implementation ([OfflineFavouriteImagesRepository.kt](app/src/main/java/com/example/gallery/data/OfflineFavouriteImagesRepository.kt)) are responsible for the interactions with the local database. The [GalleryImage.kt](app/src/main/java/com/example/gallery/model/GalleryImage.kt) represents the data that we receive from the network. The [FavouriteImage.kt](app/src/main/java/com/example/gallery/model/FavouriteImage.kt) represents a single row in the favourites image database.
* The `UI` layer is represented by the composables and the state holders (`ViewModel` classes). All the Composables in the `buttons`, `items` and `screens` packages represent elements that are used across the app. For example, the [ImageItem.kt](app/src/main/java/com/example/gallery/ui/items/ImageItem.kt) is used to represent the gallery image as well as the favourite image. The [GalleryViewModel.kt](app/src/main/java/com/example/gallery/ui/GalleryViewModel.kt) exposes properties and commands that the [GalleryScreen.kt](app/src/main/java/com/example/gallery/ui/screens/GalleryScreen.kt)-related UI can bind to, and reacts to user actions and data changes. The [FavouritesViewModel.kt](app/src/main/java/com/example/gallery/ui/FavouritesViewModel.kt) does the same for the [FavouritesScreen.kt](app/src/main/java/com/example/gallery/ui/screens/FavouritesScreen.kt)-related UI.
* The [GalleryApp.kt](app/src/main/java/com/example/gallery/ui/GalleryApp.kt) is the "main" composable that wraps the rest of composables and the only composable the `ViewModels` are called from.
* The app uses repository pattern and manual dependency injection, this is how the [GalleryViewModel.kt](app/src/main/java/com/example/gallery/ui/GalleryViewModel.kt) and [FavouritesViewModel.kt](app/src/main/java/com/example/gallery/ui/FavouritesViewModel.kt) access [FavouriteImagesRepository.kt](app/src/main/java/com/example/gallery/data/FavouriteImagesRepository.kt) and [GalleryImagesRepository.kt](app/src/main/java/com/example/gallery/data/GalleryImagesRepository.kt) (the repositories are injected).

### Saved state across configuration changes
Both [GalleryScreen.kt](app/src/main/java/com/example/gallery/ui/screens/GalleryScreen.kt) and [FavouritesScreen.kt](app/src/main/java/com/example/gallery/ui/screens/FavouritesScreen.kt) survive configuration changes (for example, screen orientation changes). 

### Data and persistence
If the user marks images as favourites, these will be stored in the [FavouriteImagesDatabase.kt](app/src/main/java/com/example/gallery/data/FavouriteImagesDatabase.kt), so if the user closes the app, the images still will be displayed in the `Favourites` tab after the restart. 

### Offline behavior
If the uses loses connection or turns on Airplane mode, the app will not be able to fetch the images from the API and the [ErrorScreen.kt](app/src/main/java/com/example/gallery/ui/screens/ErrorScreen.kt) will be shown instead of the [ResultScreen.kt](app/src/main/java/com/example/gallery/ui/screens/ResultScreen.kt). 
If the uses presses the `Retry` button on the screen, the app will try to make an API call again and as soon as the user is back online, they will be shown the [LoadingScreen.kt](app/src/main/java/com/example/gallery/ui/screens/LoadingScreen.kt) and then the gallery.

### Material Design theming
The Gallery App uses [Material Theme Builder](https://material-foundation.github.io/material-theme-builder/) and `MaterialTheme` composable. The app uses a custom, branded color scheme. 

## Libraries & dependencies used in the project:

*   Jetpack Compose (for the UI).
*   Kotlin coroutines (to load images asynchronously, handle asynchronous computations in general).
*   Kotlin serialization - tools that are needed for decoding JSON objects, converting data used by an our app to a format that can be stored in the database.
*   Retrofit (to make API calls).
*   Room - for data persistence, for storing favourite images in the database.
*   Coil (for faster image loading and improved overall performance).
*   Manual Dependency Injection.

## Credits 
The `ic_connection_error`, `ic_broken_image`, `ic_progress_activity` icons are the icons that were provided by the [Android Codelabs](https://developer.android.com/get-started/codelabs).

<a href="https://www.vecteezy.com/free-png/gallery-logo">Gallery Logo PNGs by Vecteezy</a>.


