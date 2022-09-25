package com.lineup.app.screens.HomeScreen

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material.Card
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.lineup.app.R
import com.lineup.app.models.homeScreenObject

@Composable
fun HomeScreen(homeScreenViewModel: HomeScreenViewModel) {
    Column(modifier = Modifier.fillMaxSize(), verticalArrangement = Arrangement.Top) {
        TopAppBar(title = {
            Text(text = stringResource(id = R.string.app_name))
        }, actions = {
            Icon(
                imageVector = Icons.Default.Add,
                contentDescription = "Add Icon",
                modifier = Modifier
                    .size(50.dp)
                    .clickable {
                        homeScreenViewModel.addCategory(homeScreenObject = homeScreenObject(category_Name = "Hello Dude"))
                    })
        }, backgroundColor = Color.Transparent)
        LineUpList(homeScreenViewModel)
    }
}

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun LineUpList(homeScreenViewModel: HomeScreenViewModel) {
    val homeScreenList = homeScreenViewModel.homeScreenCategories.collectAsState().value
    LazyVerticalGrid(columns = GridCells.Fixed(2), contentPadding = PaddingValues(10.dp)) {
        items(homeScreenList) {
            Card(
                modifier = Modifier
                    .size(200.dp)
                    .aspectRatio(1f)
                    .clip(RectangleShape)
                    .padding(10.dp)
                    .clickable {
                        homeScreenViewModel.deleteCategory(it)
                    }, backgroundColor = Color.Black
            ) {
                Box(contentAlignment = Alignment.Center) {
                    Text(text = it.category_Name, color = Color.White)
                }
            }
        }
    }
}