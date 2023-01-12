package com.dr.baristatis.ui.elements

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.CircularProgressIndicator
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
import com.dr.baristatis.model.MyCoffeeData
import com.dr.baristatis.ui.vm.MainViewModel

@Composable
fun CoffeeMainScreen(viewModel: MainViewModel, onMyCoffeeItemClicked: OnMyCoffeeItemClicked) {
    val viewState = viewModel.viewState.collectAsState()
    Box(Modifier.fillMaxWidth()) {
        when (viewState.value) {
            is MainViewModel.Success -> {
                CoffeeDataView(
                    (viewState.value as MainViewModel.Success).data,
                    onMyCoffeeItemClicked
                )
            }
            is MainViewModel.Error -> ErrorScreen()
            is MainViewModel.Loading -> LoadingScreen()
        }
    }
}

// TODO CompositionLocal benutzen?
@Composable
fun CoffeeDataView(coffees: List<MyCoffeeData>, onMyCoffeeItemClicked: OnMyCoffeeItemClicked) {
    if (coffees.isEmpty()) {
        EmptyListScreen()
    } else {
        LazyColumn {
            items(coffees) { coffee ->
                CoffeeCard(myCoffeeData = coffee, onMyCoffeeItemClicked = onMyCoffeeItemClicked)
            }
        }
    }
}

@Composable
fun LoadingScreen() {
    MainScreenPlaceholder(imageResource = R.drawable.empty_bag, showLoading = true)
}


@Composable
fun ErrorScreen() {
    MainScreenPlaceholder(imageResource = R.drawable.error_bag, textResource = R.string.error)
}

@Composable
fun EmptyListScreen() {
    MainScreenPlaceholder(imageResource = R.drawable.empty_bag, textResource = R.string.addCoffee)
}

@Composable
fun MainScreenPlaceholder(
    imageResource: Int,
    textResource: Int = -1,
    showLoading: Boolean = false
) {
    Column(
        Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Image(
            painter = painterResource(id = imageResource),
            contentDescription = "",
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .padding(10.dp)
                .size(200.dp)
        )
        if (textResource != -1) {
            Text(text = stringResource(id = textResource))
        }

        if (showLoading) {
            CircularProgressIndicator()
        }
    }
}
