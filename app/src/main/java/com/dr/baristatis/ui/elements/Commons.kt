package com.dr.baristatis.ui.elements

import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.dr.baristatis.R

@Composable
fun RatioPresenter(leftText: String, rightText: String, ratio: Float) {
    Column(Modifier.padding(20.dp)) {
        RatioLabel(leftText = leftText, rightText = rightText)
        LinearProgressIndicator(
            modifier = Modifier
                .fillMaxWidth()
                .height(5.dp),
            progress = ratio
        )
    }
}

@Composable
fun RatioEditor(
    leftText: String,
    rightText: String,
    ratio: Float,
    onValueChange: (Float) -> Unit
) {
    Column(Modifier.padding(20.dp)) {
        RatioLabel(leftText = leftText, rightText = rightText)
        var tmpRatio = ratio
        //Text(text = "${(tmpRatio * 100).toInt()} %", modifier = Modifier.align(Alignment.CenterHorizontally))
        Text(
            text = stringResource(id = R.string.tmpRatioSliderLabel, (tmpRatio * 100).toInt()),
            modifier = Modifier.align(Alignment.CenterHorizontally)
        )
        val ovc: (Float) -> Unit = {
            tmpRatio = it
            onValueChange.invoke(it)
        }
        Slider(value = ratio, onValueChange = ovc)
    }
}

@Composable
fun RatioLabel(leftText: String, rightText: String) {
    Row {
        Text(
            text = leftText,
            textAlign = TextAlign.Start,
            color = MaterialTheme.colors.primary
        )
        Spacer(modifier = Modifier.weight(1f))
        Text(
            text = rightText,
            textAlign = TextAlign.End,
            color = MaterialTheme.colors.primary.copy(alpha = ProgressIndicatorDefaults.IndicatorBackgroundOpacity)
        )
    }
}