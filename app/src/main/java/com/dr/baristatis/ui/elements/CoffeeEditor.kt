package com.dr.baristatis.ui.elements


import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Button
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.dr.baristatis.R
import com.dr.baristatis.model.MyCoffeeData


@Composable
fun CoffeeEditor(myCoffeeData: MyCoffeeData?, onCoffeeDataAdded: ((MyCoffeeData?) -> Unit)) {
    var name by remember { mutableStateOf(myCoffeeData?.name) }
    var manufacturer by remember { mutableStateOf(myCoffeeData?.manufacturer) }
    var arabicaRatio by remember { mutableStateOf(myCoffeeData?.arabicaRatio ?: 0.8f) }
    var remarks by remember { mutableStateOf(myCoffeeData?.remarks) }
    //myCoffeeData.weightInPortafilter
    // myCoffeeData.prefferredBrewingTemperature
    //myCoffeeData.remarks (grosses feld)

    Column(Modifier.verticalScroll(rememberScrollState())) {
        InputField(
            title = stringResource(R.string.coffee_name),
            text = name,
            onChange = { name = it })
        InputField(
            title = stringResource(R.string.manufacturer),
            text = manufacturer,
            onChange = { manufacturer = it })

        RatioEditor(
            leftText = stringResource(id = R.string.arabicaRatio), rightText = stringResource(
                id = R.string.robustaRatio
            ), ratio = arabicaRatio,
            onValueChange = { ratio -> arabicaRatio = ratio }
        )

        InputField(
            title = stringResource(R.string.remarks),
            text = remarks,
            onChange = { remarks = it }, height = 250.dp
        )

        Button(modifier = Modifier
            .padding(top = 40.dp)
            .fillMaxWidth()
            .padding(10.dp),
            onClick = {
                onCoffeeDataAdded.invoke(
                    createCoffeeData(
                        myCoffeeData,
                        name,
                        manufacturer,
                        arabicaRatio
                    )
                )
            }) {
            Text("Save")
        }
    }
}

fun createCoffeeData(
    myCoffeeData: MyCoffeeData?,
    name: String?,
    manufacturer: String?,
    arabicaRatio: Float?
): MyCoffeeData? {
    if (name != null && manufacturer != null) {
        val data = MyCoffeeData(
            myCoffeeData?.id,
            name = name,
            manufacturer = manufacturer,
            arabicaRatio = arabicaRatio
        )
        return data
    }
    return null
}

@Composable
fun InputField(
    title: String,
    text: String?,
    onChange: (String) -> Unit,
    maxLines: Int = 1,
    height: Dp? = null
) {
    OutlinedTextField(
        value = text ?: "",
        modifier = Modifier
            .fillMaxWidth()
            .padding(10.dp).let {
                return@let if (height != null) {
                    it.height(height)
                } else it
            },
        onValueChange = onChange,
        label = { Text(title) },
        maxLines = maxLines
    )
}