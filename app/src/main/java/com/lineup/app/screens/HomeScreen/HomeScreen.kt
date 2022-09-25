package com.lineup.app.screens.HomeScreen

import android.content.Context
import android.widget.Toast
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.DialogProperties
import androidx.navigation.NavHostController
import com.lineup.app.R
import com.lineup.app.models.homeScreenObject
import com.lineup.app.navigation.LineUpScreens

@Composable
fun HomeScreen(homeScreenViewModel: HomeScreenViewModel, navigationController: NavHostController) {
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
                        homeScreenViewModel.showAddCategoryDialogue.value =
                            !homeScreenViewModel.showAddCategoryDialogue.value
                    })
        }, backgroundColor = Color.Transparent)
        LineUpList(homeScreenViewModel) {
            navigationController.navigate(LineUpScreens.DetailsScreen.name + "/${it.category_Id}")
        }
        if (homeScreenViewModel.showAddCategoryDialogue.value) {
            ShowAddCategoryCard(onDismissRequest = {
                homeScreenViewModel.showAddCategoryDialogue.value =
                    !homeScreenViewModel.showAddCategoryDialogue.value
            }) {
                homeScreenViewModel.addCategory(homeScreenObject = homeScreenObject(category_Name = it))
            }
        }

        if (homeScreenViewModel.showUpdateCategoryDialogue.value) {
            homeScreenViewModel.currentCategory?.let {
                ShowUpdateCard(
                    homeScreenObject = it,
                    onDismissRequest = {
                        homeScreenViewModel.showUpdateCategoryDialogue.value =
                            !homeScreenViewModel.showUpdateCategoryDialogue.value
                    }
                ) { homeScreenObject, context ->
                    homeScreenViewModel.updateCategory(homeScreenObject = homeScreenObject)
                    Toast.makeText(context, "Category Updated!", Toast.LENGTH_SHORT).show()
                }
            }
        }

    }
}


@Composable
private fun LineUpList(
    homeScreenViewModel: HomeScreenViewModel,
    onCategoryClick: (homeScreenObject) -> Unit
) {
    val homeScreenList = homeScreenViewModel.homeScreenCategories.collectAsState().value
    LazyVerticalGrid(columns = GridCells.Adaptive(150.dp), contentPadding = PaddingValues(10.dp)) {
        items(homeScreenList) {
            Card(
                modifier = Modifier
                    .size(200.dp)
                    .aspectRatio(1f)
                    .clip(RectangleShape)
                    .padding(10.dp)
                    .clickable {
                        onCategoryClick(it)
                    }, backgroundColor = Color.Black
            ) {
                CategoryCard(it, onUpdate = {
                    homeScreenViewModel.currentCategory = it
                    homeScreenViewModel.showUpdateCategoryDialogue.value =
                        !homeScreenViewModel.showUpdateCategoryDialogue.value
                }) {
                    homeScreenViewModel.deleteCategory(it)
                }
            }
        }
    }
}

@Composable
private fun CategoryCard(
    homeScreenObject: homeScreenObject,
    onUpdate: (homeScreenObject) -> Unit,
    onDelete: (homeScreenObject) -> Unit
) {
    var showMoreOption by remember {
        mutableStateOf(false)
    }
    Box(contentAlignment = Alignment.TopEnd) {

        Box {
            IconButton(onClick = {
                showMoreOption = !showMoreOption
            }) {
                Icon(
                    imageVector = Icons.Default.MoreVert,
                    contentDescription = "Show more Options",
                    tint = MaterialTheme.colors.surface
                )

                DropdownMenu(
                    expanded = showMoreOption,
                    onDismissRequest = { showMoreOption = false }) {
                    DropdownMenuItem(onClick = {
                        onUpdate(homeScreenObject)
                        showMoreOption = !showMoreOption
                    }) {
                        Text(text = "Update")
                    }
                    DropdownMenuItem(onClick = {
                        onDelete(homeScreenObject)
                        showMoreOption = !showMoreOption
                    }) {
                        Text(text = "Delete")
                    }
                }

            }
        }
        Box(contentAlignment = Alignment.Center, modifier = Modifier.fillMaxSize()) {
            Text(text = homeScreenObject.category_Name, color = Color.White)
        }

    }
}

@Composable
fun ShowAddCategoryCard(onDismissRequest: () -> Unit, onAddCategory: (String) -> Unit) {

    var categoryName by remember {
        mutableStateOf("")
    }

    AlertDialog(
        onDismissRequest = {
            onDismissRequest()
        },
        properties = DialogProperties(dismissOnBackPress = true, dismissOnClickOutside = true),
        buttons = {
            Column(
                horizontalAlignment = Alignment.CenterHorizontally, modifier = Modifier
                    .fillMaxWidth()
                    .padding(start = 45.dp, end = 45.dp)
            ) {
                OutlinedTextField(value = categoryName, label = {
                    Text(text = "Category Name")
                }, onValueChange = {
                    categoryName = it
                }, modifier = Modifier.padding(10.dp))
                Spacer(modifier = Modifier.height(10.dp))
                Button(onClick = {
                    onAddCategory(categoryName)
                    onDismissRequest()
                }) {
                    Text(text = "Add Category")
                }
            }
        }
    )
}

@Composable
private fun ShowUpdateCard(
    homeScreenObject: homeScreenObject,
    onDismissRequest: () -> Unit,
    onUpdateCategory: (homeScreenObject, Context) -> Unit
) {

    var categoryName by remember {
        mutableStateOf(homeScreenObject.category_Name)
    }

    val currentContext = LocalContext.current

    AlertDialog(
        onDismissRequest = {
            onDismissRequest()
        },
        properties = DialogProperties(dismissOnBackPress = false, dismissOnClickOutside = false),
        buttons = {
            Column(
                horizontalAlignment = Alignment.CenterHorizontally, modifier = Modifier
                    .fillMaxWidth()
                    .padding(start = 45.dp, end = 45.dp)
            ) {
                OutlinedTextField(value = categoryName, label = {
                    Text(text = "Category Name")
                }, onValueChange = {
                    categoryName = it
                }, modifier = Modifier.padding(10.dp))
                Spacer(modifier = Modifier.height(10.dp))
                Button(onClick = {
                    homeScreenObject.category_Name = categoryName
                    onUpdateCategory(homeScreenObject, currentContext)
                    onDismissRequest()
                }) {
                    Text(text = "Update Category")
                }
            }
        }
    )
}