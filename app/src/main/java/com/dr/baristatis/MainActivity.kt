package com.dr.baristatis

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Menu
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.navArgument
import androidx.navigation.compose.rememberNavController
import com.dr.baristatis.commons.OnMyCoffeeItemClicked
import com.dr.baristatis.model.MyCoffeeData
import com.dr.baristatis.ui.elements.CoffeeCard
import com.dr.baristatis.ui.elements.CoffeeDetails
import com.dr.baristatis.ui.theme.BaristatisTheme
import com.dr.baristatis.ui.vm.MainViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel


class MainActivity : ComponentActivity() {
    private val viewModel: MainViewModel by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            Content(viewModel = viewModel)
        }
    }
}

@Composable
fun Content(viewModel: MainViewModel) {
    BaristatisTheme {
        val navController = rememberNavController()
        Scaffold(
            topBar =
            {
                TopAppBar(
                    title = { Text("Baristatis") },
                    navigationIcon = {
                        IconButton(
                            onClick = {
// https://developer.android.com/reference/kotlin/androidx/compose/material/package-summary#Scaffold(androidx.compose.ui.Modifier,androidx.compose.material.ScaffoldState,kotlin.Function0,kotlin.Function0,kotlin.Function1,kotlin.Function0,androidx.compose.material.FabPosition,kotlin.Boolean,kotlin.Function1,kotlin.Boolean,androidx.compose.ui.graphics.Shape,androidx.compose.ui.unit.Dp,androidx.compose.ui.graphics.Color,androidx.compose.ui.graphics.Color,androidx.compose.ui.graphics.Color,androidx.compose.ui.graphics.Color,androidx.compose.ui.graphics.Color,kotlin.Function1)
                                // beim öffnen denk an coroutine!
                            }
                        ) {
                            Icon(Icons.Filled.Menu, contentDescription = "Localized description")
                        }
                    }

                )
            },
            content = {
                NavHost(navController = navController, startDestination = "coffeeList") {
                    composable("coffeeList") {
                        CoffeeList(viewModel.getCoffees(), onMyCoffeeItemClicked = { item ->
                            navController.navigate("details/${item.id}")
                        })
                    }
                    composable(
                        "details/{itemId}",
                        arguments = listOf(navArgument("itemId") { type = NavType.IntType })
                    ) { backStackEntry ->
                        backStackEntry.arguments?.getInt("itemId")?.also { itemId ->
                            viewModel.getItem(itemId)?.also {
                                CoffeeDetails(it)
                            }
                        }
                    }
                }
            }
        )
    }
}

@Composable
fun CoffeeList(coffeeData: List<MyCoffeeData>, onMyCoffeeItemClicked: OnMyCoffeeItemClicked) {
    val scrollState = rememberScrollState()
    Column (Modifier.verticalScroll(scrollState)){
        coffeeData.forEach {
            CoffeeCard(myCoffeeData = it, onMyCoffeeItemClicked)
        }
    }
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    BaristatisTheme {
        //CoffeeCard(myCoffeeData = getCoffeeData())
        //   CoffeeList({})
    }
}