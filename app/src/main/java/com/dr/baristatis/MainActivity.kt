package com.dr.baristatis

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Column
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
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
            BaristatisTheme {
                // A surface container using the 'background' color from the theme
                Surface(color = MaterialTheme.colors.background) {
                    val navController = rememberNavController()
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
            }
        }
    }
}

@Composable
fun CoffeeList(coffeeData: List<MyCoffeeData>, onMyCoffeeItemClicked: OnMyCoffeeItemClicked) {
    Column {
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