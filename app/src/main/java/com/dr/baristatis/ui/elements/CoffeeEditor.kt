package com.dr.baristatis.ui.elements


import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Text
import androidx.compose.runtime.*
import com.dr.baristatis.model.MyCoffeeData

@Composable
fun CoffeeEditor(myCoffeeData: MyCoffeeData?) {
    var text by remember { mutableStateOf("Hello") }

    OutlinedTextField(
        value = text,
        onValueChange = { text = it },
        label = { Text("Label") }
    )

}