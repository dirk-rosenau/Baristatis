package com.dr.baristatis.ui.elements

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.selection.selectable
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
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
    modifier: Modifier = Modifier,
    onValueChange: (Float) -> Unit
) {
    Column(modifier) {
        RatioLabel(leftText = leftText, rightText = rightText)
        var tmpRatio = ratio
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

@Composable
fun SortDialog(radioOptions: List<String>, selectedItem: Int, onDismiss: () -> Unit, onClickOk: (Int) -> Unit) {

    Dialog(
        onDismissRequest = {
            onDismiss.invoke()
        },
        content = {
            Surface(
                shape = RoundedCornerShape(16.dp),
                color = Color.White
            ) {
                val (selectedOption, onOptionSelected) = remember { mutableStateOf(radioOptions[selectedItem]) }
                Column {
                    Text(
                        text = stringResource(id = R.string.sort),
                        Modifier
                            .align(Alignment.CenterHorizontally)
                            .padding(10.dp)
                    )
                    radioOptions.forEach { text ->
                        Row(
                            Modifier
                                .fillMaxWidth()
                                .selectable(
                                    selected = (text == selectedOption),
                                    onClick = {
                                        onOptionSelected(text)
                                    }
                                )
                                .padding(horizontal = 16.dp),
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            RadioButton(
                                selected = (text == selectedOption),
                                onClick = { onOptionSelected(text) }
                            )
                            Text(
                                text = text,
                                style = MaterialTheme.typography.body1.merge(),
                                modifier = Modifier.padding(start = 16.dp)
                            )
                        }
                    }

                    Button(
                        onClick = {
                            onClickOk(radioOptions.indexOf(selectedOption))
                            onDismiss()
                        }, Modifier
                            .align(Alignment.CenterHorizontally)
                            .padding(10.dp)
                    ) {
                        Text(text = stringResource(id = R.string.ok))
                    }
                }
            }
        }
    )
}

@Preview
@Composable
fun PreviewSortDialog() {
    SortDialog(listOf("1", "2", "3"), 0, {}, {})
}