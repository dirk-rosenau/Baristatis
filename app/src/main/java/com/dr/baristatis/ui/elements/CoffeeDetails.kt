package com.dr.baristatis.ui.elements

import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import com.dr.baristatis.model.MyCoffeeData

@Composable
fun CoffeeDetails(item: MyCoffeeData) {
    Text(text = "Test! ${item.name}")
}