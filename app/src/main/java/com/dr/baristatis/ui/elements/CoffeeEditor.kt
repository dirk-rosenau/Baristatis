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
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.dr.baristatis.R
import com.dr.baristatis.model.MyCoffeeData


@Composable
fun CoffeeEditor(myCoffeeData: MyCoffeeData?, onCoffeeDataAdded: ((MyCoffeeData?) -> Unit), onCoffeeDataDeleted: ((Int?) -> Unit)) {
    var name by remember { mutableStateOf(myCoffeeData?.name) }
    var manufacturer by remember { mutableStateOf(myCoffeeData?.manufacturer) }
    var arabicaRatio by remember { mutableStateOf(myCoffeeData?.arabicaRatio ?: 0.8f) }
    var remarks by remember { mutableStateOf(myCoffeeData?.remarks) }
    var preferreedBrewingTemperatur by remember { mutableStateOf(myCoffeeData?.prefferredBrewingTemperature) }
    var weightInPortaFilter by remember { mutableStateOf(myCoffeeData?.weightInPortafilter) }

    var showDeleteDialog by remember { mutableStateOf(false)}

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
            keyboardOptions = KeyboardOptions( imeAction = ImeAction.Next ),
            keyboardActions = KeyboardActions(
                onNext = { focusManager.moveFocus(FocusDirection.Down) }
            ))
        InputField(
            title = stringResource(R.string.manufacturer),
            text = manufacturer,
            keyboardOptions = KeyboardOptions( imeAction = ImeAction.Next ),
            onChange = { manufacturer = it })

        RatioEditor(
            leftText = stringResource(id = R.string.arabicaRatio), rightText = stringResource(
                id = R.string.robustaRatio
            ), ratio = arabicaRatio,
            onValueChange = { ratio -> arabicaRatio = ratio }
        )

        Row(
            modifier = Modifier.fillMaxSize(),
            horizontalArrangement = Arrangement.SpaceEvenly
        ) {

            // TODO icon
            Text(stringResource(id = R.string.temp))
            OutlinedTextField(
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number,imeAction = ImeAction.Next ),
                value = preferreedBrewingTemperatur?.toString() ?: "",
                onValueChange = {
                    try {
                        preferreedBrewingTemperatur = if (it.toInt() < 100) it.toInt() else 100
                    } catch (e: NumberFormatException) {
                        // do nothing
                    }
                },
                modifier = Modifier.width(100.dp),
                maxLines = 1
            )
            // TODO icon
            Text(stringResource(id = R.string.weight))
            OutlinedTextField(
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number,imeAction = ImeAction.Next ),
                value = weightInPortaFilter?.toString() ?: "",
                modifier = Modifier.width(100.dp),
                onValueChange = {
                    try {
                        weightInPortaFilter = it.toFloat()
                    } catch (e: NumberFormatException) {
                        // do nothing
                    }
                },
                maxLines = 1
            )
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
                onCoffeeDataAdded.invoke(
                    createCoffeeData(
                        myCoffeeData,
                        name,
                        manufacturer,
                        arabicaRatio,
                        remarks,
                        preferreedBrewingTemperatur,
                        weightInPortaFilter
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

        if(showDeleteDialog) {
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
                            onCoffeeDataDeleted.invoke(myCoffeeData?.id)
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

fun createCoffeeData(
    myCoffeeData: MyCoffeeData?,
    name: String?,
    manufacturer: String?,
    arabicaRatio: Float?,
    remarks: String?,
    preferredBrewingTemperature: Int?,
    weightInPortaFilter: Float?
): MyCoffeeData? {
    if (name != null && manufacturer != null) {
        return MyCoffeeData(
            myCoffeeData?.id,
            name = name,
            manufacturer = manufacturer,
            arabicaRatio = arabicaRatio,
            remarks = remarks,
            deggreeOfGrinding = null,
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
        keyboardActions = keyboardActions ?: KeyboardActions.Default,
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