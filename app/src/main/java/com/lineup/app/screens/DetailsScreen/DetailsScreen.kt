package com.lineup.app.screens.DetailsScreen

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalLifecycleOwner
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.DialogProperties
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.flowWithLifecycle
import com.lineup.app.models.LineUpObject
import com.lineup.app.models.homeScreenObject

@Composable
fun DetailsScreen(detailsScreenViewModel: DetailsScreenViewModel, categoryId: String?) {

    val lifecycleOwner = LocalLifecycleOwner.current
    val detailsListFlowLifecycleAware = remember(
        detailsScreenViewModel.getAllDetailsInThisCategory(category_id = categoryId.toString()),
        lifecycleOwner
    ) {
        detailsScreenViewModel.getAllDetailsInThisCategory(category_id = categoryId.toString())
            .flowWithLifecycle(lifecycleOwner.lifecycle, Lifecycle.State.STARTED)
    }
    val detailsList: List<LineUpObject> by detailsListFlowLifecycleAware.collectAsState(initial = emptyList())


    val categoryDetails = produceState(initialValue = homeScreenObject(category_Name = "")) {
        value = detailsScreenViewModel.getCategory(category_id = categoryId.toString())
    }.value



    Column(modifier = Modifier.fillMaxSize(), verticalArrangement = Arrangement.Top) {
        TopAppBar(title = {
            Text(text = categoryDetails.category_Name)
        }, actions = {
            Icon(
                imageVector = Icons.Default.Add,
                contentDescription = "Add Icon",
                modifier = Modifier
                    .size(50.dp)
                    .clickable {
                        detailsScreenViewModel.showAddDetailsDialogue.value =
                            !detailsScreenViewModel.showAddDetailsDialogue.value
                    })
        }, backgroundColor = Color.Transparent)

        DetailsList(detailsList, categoryDetails)

        if (detailsScreenViewModel.showAddDetailsDialogue.value) {
            addDetails(categoryId = categoryId, onDismissRequest = {
                detailsScreenViewModel.showAddDetailsDialogue.value =
                    !detailsScreenViewModel.showAddDetailsDialogue.value
            }) {
                detailsScreenViewModel.addDetail(lineUpObject = it)
            }
        }

    }
}

@Composable
private fun DetailsList(
    detailsList: List<LineUpObject>,
    categoryDetails: homeScreenObject
) {
    if (detailsList.isNullOrEmpty()) {
        Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
        Text(text = "Click + to add new ${categoryDetails.category_Name}", style = TextStyle(color = MaterialTheme.colors.onSurface))
        }
    } else {
        LazyColumn {
            items(detailsList)
            {
                Card(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(100.dp)
                        .padding(25.dp), elevation = 10.dp
                ) {
                    Column(
                        horizontalAlignment = Alignment.CenterHorizontally,
                        verticalArrangement = Arrangement.Center
                    ) {
                        Text(text = it.name)
                    }
                }
            }
        }
    }
}

@Composable
private fun addDetails(
    categoryId: String?,
    onDismissRequest: () -> Unit,
    onAddDetail: (LineUpObject) -> Unit
) {

    var detailName by remember {
        mutableStateOf("")
    }

    var detailYear by remember {
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
                OutlinedTextField(value = detailName, label = {
                    Text(text = "Category Name")
                }, onValueChange = {
                    detailName = it
                }, modifier = Modifier.padding(10.dp))
                Spacer(modifier = Modifier.height(10.dp))

                OutlinedTextField(value = detailYear, label = {
                    Text(text = "Year")
                }, onValueChange = {
                    detailYear = it
                }, modifier = Modifier.padding(10.dp))
                Spacer(modifier = Modifier.height(10.dp))

                Button(onClick = {
                    onAddDetail(
                        LineUpObject(
                            name = detailName,
                            year = detailYear,
                            category_id = categoryId.toString()
                        )
                    )
                    onDismissRequest()
                }) {
                    Text(text = "Add to List")
                }
            }
        })
}