package com.dr.baristatis.ui.elements

import androidx.compose.animation.animateContentSize
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.dr.baristatis.R
import com.dr.baristatis.commons.OnMyCoffeeItemClicked
import com.dr.baristatis.model.MyCoffeeData
import com.gowtham.ratingbar.RatingBar
import com.gowtham.ratingbar.RatingBarConfig
import com.gowtham.ratingbar.RatingBarStyle


@Composable
fun CoffeeCard(myCoffeeData: MyCoffeeData, onMyCoffeeItemClicked: OnMyCoffeeItemClicked) {
    val maxLines = remember { mutableStateOf(3) }
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
                /* Image(
                     painter = painterResource(id = R.drawable.coffee_beans), // TODO use image from data
                     contentDescription = "",
                     contentScale = ContentScale.Crop,
                     modifier = Modifier
                         .padding(10.dp)
                         .size(72.dp)
                         .clip(CircleShape)
                 )*/
                Spacer(modifier = Modifier.padding(10.dp))
                Column(Modifier.weight(4f)) {
                    Text(text = myCoffeeData.name, fontSize = 24.sp)
                    Text(text = myCoffeeData.manufacturer, fontSize = 14.sp)
                }
                Column(modifier = Modifier.weight(1f)) {
                    myCoffeeData.prefferredBrewingTemperature?.let {
                        Text(text = "$it °")
                    }

                    myCoffeeData.weightInPortafilter?.let {
                        Text(text = "$it g")
                    }
                }
            }

            RatingBar(
                modifier = Modifier
                    .align(Alignment.End)
                    .padding(PaddingValues(top = 10.dp, end = 10.dp)),
                value = myCoffeeData.rating ?: 0f,
                config = RatingBarConfig().size(20.dp)
                    .style(RatingBarStyle.HighLighted).isIndicator(true),
                onValueChange = {},
                onRatingChanged = {}
            )

            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier.padding(10.dp)
            )
            { // second row: ratio
                Text(text = "${stringResource(R.string.ingredients)}:")

                RatioPresenter(
                    leftText = stringResource(R.string.arabicaRatio),
                    rightText = stringResource(R.string.robustaRatio),
                    ratio = myCoffeeData.arabicaRatio ?: 0.8f
                )
            }

            if (myCoffeeData.remarks != null) {
                Text(
                    text = "${stringResource(id = R.string.remarks)}:",
                    modifier = Modifier.getDefaultTextPadding()
                )
                Text(
                    text = myCoffeeData.remarks,
                    modifier = Modifier
                        .fillMaxWidth()
                        .getDefaultTextPadding()
                        .border(
                            width = 1.dp,
                            color = MaterialTheme.colors.primary,
                            shape = RoundedCornerShape(4.dp)
                        )
                        .padding(10.dp)
                        .animateContentSize()
                        .clickable {
                            maxLines.value =
                                if (maxLines.value == Int.MAX_VALUE) 5 else Int.MAX_VALUE
                        },
                    overflow = TextOverflow.Ellipsis,
                    maxLines = maxLines.value,
                )
            }
        }
    }
}

fun Modifier.getDefaultTextPadding(): Modifier {
    return this.padding(start = 10.dp, end = 10.dp, bottom = 5.dp)
}