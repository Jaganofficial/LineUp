package com.lineup.app.screens.MoreDetailsScreen

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.runtime.Composable
import androidx.compose.runtime.produceState
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import coil.compose.rememberAsyncImagePainter
import coil.request.ImageRequest
import coil.size.Scale
import com.lineup.app.models.LineUpObject

@Composable
fun MoreDetailScreen(
    navigationController: NavHostController,
    lineup_object_id: String?,
    moreDetailsScreenViewModel: MoreDetailsScreenViewModel
) {

    val details =
        produceState(initialValue = LineUpObject(name = "", year = "", category_id = "")) {
            value =
                moreDetailsScreenViewModel.getDetail(lineUpObjectId = lineup_object_id.toString())
        }.value

    Column(modifier = Modifier.fillMaxSize()) {
        TopThumbnail(imageUrl = details.image_url)
    }
}

@Composable
private fun TopThumbnail(imageUrl: String) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .height(350.dp)
    ) {
        Box(modifier = Modifier.fillMaxSize()){

        if (imageUrl.isEmpty()) {
            Image(
                painter = painterResource(id = com.lineup.app.R.drawable.line_up_placeholder),
                contentDescription = "Default Image",
                modifier = Modifier.fillMaxSize()
            )
        } else {
            val imagePainter = rememberAsyncImagePainter(
                model = ImageRequest.Builder(LocalContext.current)
                    .data(imageUrl)
                    .placeholder(com.lineup.app.R.drawable.line_up_placeholder)
                    .scale(Scale.FILL)
                    .build()
            )
            Image(
                painter = imagePainter,
                contentDescription = "Detail image",
                contentScale = ContentScale.Crop,
                modifier = Modifier.clip(
                    RoundedCornerShape(6.dp)
                ).fillMaxSize()
            )
        }
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .background(
                        brush = Brush.verticalGradient(
                            listOf(
                                Color.Transparent,
                                Color.LightGray
                            )
                        )
                    )
            )
        }
    }
}