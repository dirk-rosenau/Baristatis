package com.dr.baristatis

import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.Card
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.compose.rememberNavController
import com.dr.baristatis.ui.theme.BaristatisTheme
import com.dr.baristatis.ui.vm.MainViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel

class MainActivity : ComponentActivity() {
    private val viewModel: MainViewModel by viewModel<MainViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            BaristatisTheme {
                // A surface container using the 'background' color from the theme
                Surface(color = MaterialTheme.colors.background) {

                    CoffeeList(viewModel.getCoffees(), onMyCoffeeItemClicked = { item ->
                        Toast.makeText(
                            applicationContext,
                            item.name, Toast.LENGTH_LONG
                        ).show()
                    })
                }
            }
        }
    }
}


@Composable
fun CoffeeList(coffeeData: List<MyCoffeeData>, onMyCoffeeItemClicked: OnMyCoffeeItemClicked) {
    Column {
        coffeeData.forEach{
            CoffeeCard(myCoffeeData = it, onMyCoffeeItemClicked)
        }
    }
}

typealias OnMyCoffeeItemClicked = (MyCoffeeData) -> Unit

@Composable
fun CoffeeCard(myCoffeeData: MyCoffeeData, onMyCoffeeItemClicked: OnMyCoffeeItemClicked) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(10.dp)
            .clickable { onMyCoffeeItemClicked(myCoffeeData) },
        elevation = 10.dp
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically
        ) {
            Image(
                painter = painterResource(id = R.drawable.coffee_beans),
                contentDescription = "",
                modifier = Modifier
                    .size(48.dp)
                    .clip(CircleShape)                       // clip to the circle shape
                    .border(1.dp, Color.Gray, CircleShape)
            )  // add a border (optional))
            Column {
                Text(text = myCoffeeData.manufacturer)
                Text(text = myCoffeeData.name, fontSize = 10.sp)
            }
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