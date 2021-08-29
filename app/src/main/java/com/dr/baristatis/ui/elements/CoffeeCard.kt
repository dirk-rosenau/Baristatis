package com.dr.baristatis.ui.elements

import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
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
        Column { // outer column
            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier.fillMaxWidth()
            ) { // first section: image, names temp and weight
                Image(
                    painter = painterResource(id = R.drawable.coffee_beans), // TODO use image from data
                    contentDescription = "",
                    contentScale = ContentScale.Crop,
                    modifier = Modifier
                        .padding(10.dp)
                        .size(72.dp)
                        .clip(CircleShape)
                )
                Column(Modifier.weight(4f)) {
                    Text(text = myCoffeeData.name, fontSize = 24.sp)
                    Text(text = myCoffeeData.manufacturer, fontSize = 14.sp)
                }
                Column(modifier = Modifier.weight(1f)) {
                    Text(text = "${myCoffeeData.prefferredBrewingTemperature} °")
                    Text(text = "${myCoffeeData.weightInPortafilter} g")
                }
            }
            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier.padding(10.dp)
            )
            { // second row: ratio
                Text(text = stringResource(R.string.ingredients))

                Column(Modifier.padding(20.dp)) {
                    Row {
                        Text(
                            text = "Arabica",
                            textAlign = TextAlign.Start,
                            color = MaterialTheme.colors.primary
                        )
                        Spacer(modifier = Modifier.weight(1f))
                        Text(
                            text = "Robusta",
                            textAlign = TextAlign.End,
                            color = MaterialTheme.colors.primary.copy(alpha = ProgressIndicatorDefaults.IndicatorBackgroundOpacity)
                        )
                    }
                    LinearProgressIndicator(
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(5.dp), progress = myCoffeeData.arabicaRatio
                    )
                }
            }
            Text(
                text = stringResource(id = R.string.remarks),
                modifier = Modifier.getDefaultTextPadding()
            ) // TODO in Rahmen Zeichnen
            Text(
                text = "Lorem ipsum dolor sit amet, consetetur sadipscing elitr, sed diam nonumy eirmod tempor invidunt ut labore et dolore magna aliquyam erat, sed diam voluptua. At vero eos et accusam et justo duo dolores et ea rebum. Stet clita kasd gubergren, no sea takimata sanctus est Lorem ipsum dolor sit amet. Lorem ipsum dolor sit amet, consetetur sadipscing elitr, sed diam nonumy eirmod tempor invidunt ut labore et dolore magna aliquyam erat, sed diam voluptua. At vero eos et accusam et justo duo dolores et ea rebum. Stet clita kasd gubergren, no sea takimata sanctus est Lorem ipsum dolor sit amet.",
                modifier = Modifier
                    .getDefaultTextPadding()
                    .border(
                        width = 1.dp,
                        color = MaterialTheme.colors.primary,
                        shape = RoundedCornerShape(4.dp)
                    )
                    .padding(10.dp),
                overflow = TextOverflow.Ellipsis,
                maxLines = 3
            )

        }
    }
}

fun Modifier.getDefaultTextPadding(): Modifier {
    return this.padding(start = 10.dp, end = 10.dp, bottom = 5.dp)
}