package com.lineup.app

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.GridCells
import androidx.compose.foundation.lazy.LazyVerticalGrid
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.core.content.ContextCompat
import com.lineup.app.ui.theme.LineUpTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MyApp {
                Scaffold(topBar = {
                    TopAppBar(backgroundColor = Color.Transparent, elevation = 2.dp) {
                        Row(
                            verticalAlignment = Alignment.CenterVertically,
                            horizontalArrangement = Arrangement.SpaceBetween,
                            modifier=Modifier.fillMaxWidth()
                        ) {
                            Spacer(modifier = Modifier.width(1.dp))
                            Text(
                                text = "LineUp",
                                style = TextStyle(
                                    color = MaterialTheme.colors.onBackground,
                                    fontWeight = FontWeight.SemiBold,
                                    fontSize = 25.sp
                                ),
                                modifier = Modifier.padding(start = 10.dp)
                            )
                            Icon(
                                imageVector = Icons.Default.Add,
                                contentDescription = "Add icon",
                                modifier = Modifier
                                    .padding(end = 10.dp)
                                    .background(color = Color.LightGray)
                                    .clip(shape = RoundedCornerShape(10.dp))
                                    .size(35.dp)
                                    .padding(10.dp), tint = Color.White
                            )
                        }
                    }
                }) {
                    MainContent()
                }
            }
        }
    }
}

@Composable
private fun MyApp(content: @Composable () -> Unit = {}) {
    LineUpTheme {
        content()
    }
}

@Preview
@OptIn(ExperimentalFoundationApi::class)
@Composable
fun MainContent(){
    Surface(modifier = Modifier.fillMaxSize()) {
        LazyVerticalGrid(cells = GridCells.Fixed(2)){
            items(3){
                if(it!=2)
                {
                    Box(modifier = Modifier
                        .padding(10.dp)
                        .aspectRatio(1f)
                        .clip(RoundedCornerShape(10.dp))
                        .background(Color.DarkGray), contentAlignment = Alignment.Center){
                        Text(text = "${it+1}", style = TextStyle(color = Color.White, fontSize = 25.sp))
                    }
                }
                else
                {
                    Box(modifier = Modifier
                        .padding(10.dp)
                        .aspectRatio(1f)
                        .clip(RoundedCornerShape(10.dp))
                        .background(Color.DarkGray), contentAlignment = Alignment.Center){
                        Icon(imageVector = Icons.Default.Add, contentDescription = "Add Icon to add New Section", tint = Color.White, modifier = Modifier
                            .size(65.dp)
                            .clip(
                                RoundedCornerShape(55.dp)
                            )
                            .background(color = Color.Black)
                            .padding(15.dp))
                    }
                }
            }
        }
    }
}

