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

    Column {
        InputField(
            title = stringResource(R.string.coffee_name),
            text = name,
            onChange = { name = it })
        InputField(
            title = stringResource(R.string.manufacturer),
            text = manufacturer,
            onChange = { manufacturer = it })

        Button(onClick = {
            onCoffeeDataAdded.invoke(
                createCoffeeData(
                    myCoffeeData,
                    name,
                    manufacturer
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
    manufacturer: String?
): MyCoffeeData? {
    if (name != null && manufacturer != null) {
        val data = MyCoffeeData(
            myCoffeeData?.id,
            name = name,
            manufacturer = manufacturer
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