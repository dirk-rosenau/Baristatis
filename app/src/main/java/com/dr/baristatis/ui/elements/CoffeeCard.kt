package com.dr.baristatis.ui.elements

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.Card
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.dr.baristatis.R
import com.dr.baristatis.commons.OnMyCoffeeItemClicked
import com.dr.baristatis.model.MyCoffeeData


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
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.fillMaxWidth()
        ) {
            Image(
                painter = painterResource(id = R.drawable.coffee_beans), // TODO use image from data
                contentDescription = "",
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .padding(10.dp)
                    .size(48.dp)
                    .clip(CircleShape)                       // clip to the circle shape

            )  // add a border (optional))
            Column(Modifier.weight(4f)) {
                Text(text = myCoffeeData.manufacturer)
                Text(text = myCoffeeData.name, fontSize = 10.sp)
            }
            Column(modifier = Modifier.weight(1f)) {
                Text(text = "${myCoffeeData.prefferredBrewingTemperature} °")
                Text(text = "${myCoffeeData.weightInPortafilter} g")
            }
        }
    }

}