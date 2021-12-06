package com.dr.baristatis.ui.elements


import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Button
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.dr.baristatis.R
import com.dr.baristatis.model.MyCoffeeData


@Composable
fun CoffeeEditor(myCoffeeData: MyCoffeeData?, onCoffeeDataAdded: ((MyCoffeeData?) -> Unit)) {
    var name by remember { mutableStateOf(myCoffeeData?.name) }
    var manufacturer by remember { mutableStateOf(myCoffeeData?.manufacturer) }
    var arabicaRatio by remember { mutableStateOf(myCoffeeData?.arabicaRatio ?: 0.8f) }
    //myCoffeeData.weightInPortafilter
    // myCoffeeData.prefferredBrewingTemperature
    // myCoffeeData.arabicaRatio / robusta ratio
    //myCoffeeData.remarks (grosses feld)

    Column {
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
fun InputField(title: String, text: String?, onChange: (String) -> Unit) {
    OutlinedTextField(
        value = text ?: "",
        modifier = Modifier
            .fillMaxWidth()
            .padding(10.dp),
        onValueChange = onChange,
        label = { Text(title) }
    )
}