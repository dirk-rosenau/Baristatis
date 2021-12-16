package com.dr.baristatis.ui.elements

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.dr.baristatis.R
import com.dr.baristatis.commons.OnMyCoffeeItemClicked
import com.dr.baristatis.ui.vm.MainViewModel

@Composable
fun CoffeeMainScreen(viewModel: MainViewModel, onMyCoffeeItemClicked: OnMyCoffeeItemClicked) {
    val coffees = viewModel.coffees.collectAsState()
    Box(Modifier.fillMaxWidth()) {
        if (coffees.value.isEmpty()) {
            EmptyMainScreen()
        } else {
            LazyColumn {
                items(coffees.value) { coffee ->
                    CoffeeCard(myCoffeeData = coffee, onMyCoffeeItemClicked = onMyCoffeeItemClicked)
                }
            }
        }
    }
}

@Composable
fun EmptyMainScreen() {
    Column(
        Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Image(
            painter = painterResource(id = R.drawable.empty_bag), // TODO use image from data
            contentDescription = "",
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .padding(10.dp)
                .size(200.dp)

        )
        Text(text = stringResource(id = R.string.addCoffee))
    }
}
