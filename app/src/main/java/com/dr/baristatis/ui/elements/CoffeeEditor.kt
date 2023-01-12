package com.dr.baristatis.ui.elements

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.AlertDialog
import androidx.compose.material.Button
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusDirection
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.dr.baristatis.R
import com.dr.baristatis.model.MyCoffeeData
import com.gowtham.ratingbar.RatingBar
import com.gowtham.ratingbar.RatingBarConfig
import com.gowtham.ratingbar.RatingBarStyle

@Preview
@Composable
fun CoffeeEditorPreview() {
    CoffeeEditor(myCoffeeData = null, onCoffeeDataAdded = null, onCoffeeDataDeleted = null)
}

@Composable
fun CoffeeEditor(
    myCoffeeData: MyCoffeeData?,
    onCoffeeDataAdded: ((MyCoffeeData?) -> Unit)?,
    onCoffeeDataDeleted: ((Int?) -> Unit)?
) {
    var name by remember { mutableStateOf(myCoffeeData?.name) }
    var manufacturer by remember { mutableStateOf(myCoffeeData?.manufacturer) }
    var arabicaRatio by remember { mutableStateOf(myCoffeeData?.arabicaRatio ?: 0.8f) }
    var remarks by remember { mutableStateOf(myCoffeeData?.remarks) }
    var preferreedBrewingTemperatur by remember { mutableStateOf(myCoffeeData?.prefferredBrewingTemperature) }
    var weightInPortaFilter by remember { mutableStateOf(myCoffeeData?.weightInPortafilter) }
    var degreeOfGrinding by remember { mutableStateOf(myCoffeeData?.deggreeOfGrinding) }
    var grinderTime by remember { mutableStateOf(myCoffeeData?.grinderTime) }
    var rating by remember { mutableStateOf(myCoffeeData?.rating) }

    var showDeleteDialog by remember { mutableStateOf(false) }

    Column(
        Modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState())
    ) {
        val focusManager = LocalFocusManager.current
        InputField(
            title = stringResource(R.string.coffee_name),
            text = name,
            onChange = { name = it },
            keyboardOptions = KeyboardOptions(imeAction = ImeAction.Next),
            keyboardActions = KeyboardActions(
                onNext = { focusManager.moveFocus(FocusDirection.Down) }
            ))
        InputField(
            title = stringResource(R.string.manufacturer),
            text = manufacturer,
            keyboardOptions = KeyboardOptions(imeAction = ImeAction.Next),
            onChange = { manufacturer = it })

        Row(
            modifier = Modifier
                .fillMaxSize()
                .padding(20.dp),
            horizontalArrangement = Arrangement.SpaceEvenly
        ) {
            RatingBar(
                value = rating ?: 0f,
                config = RatingBarConfig()
                    .style(RatingBarStyle.HighLighted),
                onValueChange = {
                    rating = it
                },
                onRatingChanged = {}
            )
        }

        RatioEditor(
            leftText = stringResource(id = R.string.arabicaRatio), rightText = stringResource(
                id = R.string.robustaRatio
            ), ratio = arabicaRatio,
            modifier = Modifier.padding(20.dp),
            onValueChange = { ratio -> arabicaRatio = ratio }
        )

        val gridPadding = 10.dp

        Column(modifier = Modifier.padding(10.dp)) {
            Row(
                Modifier
                    .fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceEvenly
            ) {

                // TODO icon
                Text(
                    stringResource(id = R.string.temp), modifier = Modifier
                        .weight(1f)
                        .padding(gridPadding)
                )
                OutlinedTextField(
                    keyboardOptions = KeyboardOptions(
                        keyboardType = KeyboardType.Number,
                        imeAction = ImeAction.Next
                    ),
                    value = preferreedBrewingTemperatur?.toString() ?: "",
                    onValueChange = {
                        try {
                            preferreedBrewingTemperatur = if (it.toInt() < 100) it.toInt() else 100
                        } catch (e: NumberFormatException) {
                            preferreedBrewingTemperatur = null
                        }
                    },
                    modifier = Modifier
                        .weight(1f)
                        .padding(gridPadding),
                    maxLines = 1
                )
                // TODO icon
                Text(
                    stringResource(id = R.string.weight), modifier = Modifier
                        .weight(1f)
                        .padding(gridPadding)
                )
                OutlinedTextField(
                    keyboardOptions = KeyboardOptions(
                        keyboardType = KeyboardType.Number,
                        imeAction = ImeAction.Next
                    ),
                    value = weightInPortaFilter?.toString() ?: "",
                    modifier = Modifier
                        .weight(1f)
                        .padding(gridPadding),
                    onValueChange = {
                        weightInPortaFilter = try {
                            it.toFloat()
                        } catch (e: NumberFormatException) {
                            null
                        }
                    },
                    maxLines = 1
                )
            }

            Row(
                modifier = Modifier.fillMaxSize(),
                horizontalArrangement = Arrangement.SpaceEvenly
            ) {

                // TODO icon
                Text(
                    stringResource(id = R.string.degOfGrind),
                    modifier = Modifier
                        .weight(1f)
                        .padding(gridPadding)
                )
                OutlinedTextField(
                    keyboardOptions = KeyboardOptions(
                        keyboardType = KeyboardType.Number,
                        imeAction = ImeAction.Next
                    ),
                    value = degreeOfGrinding?.toString() ?: "",
                    onValueChange = {
                        try {
                            degreeOfGrinding = if (it.toInt() < 100) it.toInt() else 100
                        } catch (e: NumberFormatException) {
                            degreeOfGrinding = null
                        }
                    },
                    modifier = Modifier
                        .weight(1f)
                        .padding(gridPadding),
                    maxLines = 1
                )
                // TODO icon
                Text(
                    stringResource(id = R.string.grindTime), modifier = Modifier
                        .weight(1f)
                        .padding(gridPadding)
                )
                OutlinedTextField(
                    keyboardOptions = KeyboardOptions(
                        keyboardType = KeyboardType.Number,
                        imeAction = ImeAction.Next
                    ),
                    value = grinderTime?.toString() ?: "",
                    modifier = Modifier
                        .weight(1f)
                        .padding(gridPadding),
                    onValueChange = {
                        grinderTime = try {
                            it.toFloat()
                        } catch (e: NumberFormatException) {
                            null
                        }
                    },
                    maxLines = 1
                )
            }
        }


        InputField(
            title = stringResource(R.string.remarks),
            text = remarks,
            onChange = { remarks = it }, height = 250.dp,
            maxLines = 10
        )

        Button(modifier = Modifier
            .fillMaxWidth()
            .padding(top = 20.dp, start = 10.dp, end = 10.dp),
            onClick = {
                onCoffeeDataAdded?.invoke(
                    getCoffeeDataObject(
                        myCoffeeData,
                        name,
                        manufacturer,
                        arabicaRatio,
                        remarks,
                        preferreedBrewingTemperatur,
                        weightInPortaFilter,
                        rating
                    )
                )
            }) {
            Text(stringResource(id = R.string.save))
        }

        Button(modifier = Modifier
            .fillMaxWidth()
            .padding(start = 10.dp, end = 10.dp),
            onClick = {
                showDeleteDialog = true
            }) {
            Text(stringResource(id = R.string.delete))
        }

        if (showDeleteDialog) {
            AlertDialog(
                onDismissRequest = {
                    // Dismiss the dialog when the user clicks outside the dialog or on the back
                    // button. If you want to disable that functionality, simply use an empty
                    // onCloseRequest.
                    showDeleteDialog = false
                },
                title = {
                    Text(text = stringResource(id = R.string.delete))
                },
                text = {
                    Text(stringResource(id = R.string.confirm_delete_explain))
                },
                confirmButton = {
                    Button(

                        onClick = {
                            showDeleteDialog = false
                            onCoffeeDataDeleted?.invoke(myCoffeeData?.id)
                        }) {
                        Text(stringResource(id = R.string.yes))
                    }
                },
                dismissButton = {
                    Button(

                        onClick = {
                            showDeleteDialog = false
                        }) {
                        Text(stringResource(id = R.string.no))
                    }
                }
            )
        }
    }
}

fun getCoffeeDataObject(
    myCoffeeData: MyCoffeeData?,
    name: String?,
    manufacturer: String?,
    arabicaRatio: Float?,
    remarks: String?,
    preferredBrewingTemperature: Int?,
    weightInPortaFilter: Float?,
    rating: Float?,
): MyCoffeeData? {
    if (name != null && manufacturer != null) {
        return MyCoffeeData(
            myCoffeeData?.id,
            name = name,
            manufacturer = manufacturer,
            arabicaRatio = arabicaRatio,
            remarks = remarks,
            deggreeOfGrinding = null,
            rating = rating,
            prefferredBrewingTemperature = preferredBrewingTemperature,
            weightInPortafilter = weightInPortaFilter
        )
    }
    return null
}

@Composable
fun InputField(
    title: String,
    text: String?,
    @SuppressLint("ModifierParameter") modifier: Modifier? = null,
    onChange: (String) -> Unit,
    maxLines: Int = 1,
    height: Dp? = null,
    keyboardOptions: KeyboardOptions? = null,
    keyboardActions: KeyboardActions? = null
) {
    OutlinedTextField(
        singleLine = maxLines == 1,
        keyboardOptions = keyboardOptions ?: KeyboardOptions.Default,
        value = text ?: "",
        modifier = Modifier
            .fillMaxWidth()
            .padding(10.dp)
            .let {
                return@let modifier ?: it
            }
            .let {
                return@let if (height != null) {
                    it.height(height)
                } else it
            },
        onValueChange = onChange,
        label = { Text(title) },
        maxLines = maxLines
    )
}