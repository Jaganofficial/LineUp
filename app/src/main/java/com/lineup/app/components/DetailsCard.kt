package com.lineup.app.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.rememberAsyncImagePainter
import coil.request.ImageRequest
import coil.size.Scale
import com.lineup.app.models.LineUpObject

@Composable
@Preview
fun DetailsCard(
    modifier:Modifier=Modifier,
    lineUpObject: LineUpObject = LineUpObject(
        name = "Free Guy",
        year = "2021",
        platform = "Internet",
        genre = "Action/Sci-fi",
        description = "Already watched. very cool movie. Want to watch it again after some time. very cool movie. Want to watch it again after some time.",
        image_url = "https://lumiere-a.akamaihd.net/v1/images/p_20cs_freeguy_homeent_21930_49e74453.jpeg?region=0%2C0%2C540%2C810",
        category_id = "1"
    )
) {
    Card(
        modifier = modifier
            .fillMaxWidth()
            .padding(15.dp), elevation = 15.dp, shape = RoundedCornerShape(15.dp)
    ) {
        Row(modifier = Modifier.padding(10.dp)) {
            Box(contentAlignment = Alignment.CenterStart) {
                Card(
                    modifier = Modifier
                        .height(175.dp)
                        .width(120.dp), elevation = 10.dp,shape= RoundedCornerShape(10.dp)
                ) {
                    if ((lineUpObject.image_url).isEmpty()) {
                        Image(
                            painter = painterResource(id = com.lineup.app.R.drawable.line_up_placeholder),
                            contentDescription = "${lineUpObject.name} image",
                            contentScale = ContentScale.Crop, modifier = Modifier.clip(
                                RoundedCornerShape(6.dp)
                            )
                        )
                    } else {
                        val imagePainter = rememberAsyncImagePainter(
                            model = ImageRequest.Builder(LocalContext.current)
                                .data(lineUpObject.image_url)
                                .placeholder(com.lineup.app.R.drawable.line_up_placeholder)
                                .scale(Scale.FILL)
                                .build()
                        )
                        Image(
                            painter = imagePainter,
                            contentDescription = "${lineUpObject.name} image",
                            contentScale = ContentScale.Crop,
                            modifier = Modifier.clip(
                                RoundedCornerShape(6.dp)
                            )
                        )
                    }
                }
            }

            Column(modifier = Modifier.padding(start = 10.dp)) {
                Spacer(modifier = Modifier.height(5.dp))
                Text(
                    text = "${lineUpObject.name} ",
                    style = TextStyle(
                        color = MaterialTheme.colors.onSurface,
                        fontWeight = FontWeight.SemiBold,
                        fontSize = 21.sp
                    )
                )
                Spacer(modifier = Modifier.height(5.dp))
                Text(
                    text = "${lineUpObject.year} ",
                    style = TextStyle(color = Color.LightGray, fontSize = 15.sp)
                )
                Spacer(modifier = Modifier.height(5.dp))
                Text(
                    text = "${lineUpObject.description} ",
                    style = TextStyle(color = MaterialTheme.colors.onSurface, fontSize = 14.sp),
                    modifier = Modifier.alpha(0.8f),
                    maxLines = 3,
                    overflow = TextOverflow.Ellipsis
                )
            }
        }
    }
}