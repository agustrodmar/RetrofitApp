
package com.example.marsphotos.ui.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.rememberAsyncImagePainter
import com.example.marsphotos.R
import com.example.marsphotos.network.MarsPhoto
import com.example.marsphotos.ui.theme.MarsPhotosTheme




/**
 * A composable function that displays different screens based on the state of MarsUiState.
 *
 * @param marsUiState The state of the Mars UI.
 * @param modifier The modifier to be applied to the layout composable.
 */
@Composable
fun HomeScreen(
    marsUiState: MarsUiState,
    modifier: Modifier = Modifier
) {
    when (marsUiState) {
        is MarsUiState.Loading -> LoadingScreen(modifier = modifier.fillMaxSize())
        is MarsUiState.Success -> ResultScreen(
            marsUiState.photos, modifier = modifier.fillMaxWidth()
        )

        is MarsUiState.Error -> ErrorScreen( modifier = modifier.fillMaxSize())
    }
}


/**
 * A composable function that displays a loading image.
 *
 * @param modifier The modifier to be applied to the image composable.
 */
@Composable
fun LoadingScreen(modifier: Modifier = Modifier) {
    Image(
        modifier = modifier.size(200.dp),
        painter = painterResource(R.drawable.loading_img),
        contentDescription = stringResource(R.string.loading)
    )
}

/**
 * A composable function that displays an error image and a text message.
 *
 * @param modifier The modifier to be applied to the layout composable.
 */
@Composable
fun ErrorScreen(modifier: Modifier = Modifier) {
    Column(
        modifier = modifier,
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Image(
            painter = painterResource(id = R.drawable.ic_connection_error), contentDescription = ""
        )
        Text(text = stringResource(R.string.loading_failed), modifier = Modifier.padding(16.dp))
    }
}

/**
 * A composable function that displays the number of photos retrieved.
 *
 * @param photos The number of photos retrieved.
 * @param modifier The modifier to be applied to the layout composable.
 */
@Composable
fun ResultScreen(photos: List<MarsPhoto>, modifier: Modifier = Modifier) {
    LazyColumn(modifier = modifier) {
        items(photos) { photo ->
            ImageCard(photo = photo)
        }
    }
}
/**
 * A preview composable function for ResultScreen.
 */
@Preview(showBackground = true)
@Composable
fun ResultScreenPreview() {
    MarsPhotosTheme {
        val previewPhotos = listOf(
            MarsPhoto(id = "1", imgSrc = "https://example.com/photo1.jpg"),
            MarsPhoto(id = "2", imgSrc = "https://example.com/photo2.jpg")
        )
        ResultScreen(previewPhotos)
    }
}


/**
 * A composable function that displays an ImageCard with the given MarsPhoto.
 *
 * @param photo The MarsPhoto to be displayed.
 */
@Composable
fun ImageCard(photo: MarsPhoto) {
    Card(
        shape = RoundedCornerShape(4.dp),
        modifier = Modifier.padding(8.dp)
    ) {
        Image(
            painter = rememberAsyncImagePainter(model = photo.imgSrc),
            contentDescription = "Mars Photo ID: ${photo.id}",
            modifier = Modifier.height(200.dp),
            contentScale = ContentScale.Crop
        )
    }
}


