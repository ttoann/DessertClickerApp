package com.example.dessertclicker.data

import androidx.annotation.DrawableRes
import com.example.dessertclicker.data.Datasource.dessertList

data class DessertUIState(
    val currentDessert1Index: Int = 0,
    val currentDessert2Index: Int = 2,
    val dessertsSold: Int = 0,
    val revenue: Int = 0,
    val currentDessert1Price: Int = dessertList[currentDessert1Index].price,
    val currentDessert2Price: Int = dessertList[currentDessert2Index].price,
    @DrawableRes val currentDessert1ImageId: Int = dessertList[currentDessert1Index].imageId,
    @DrawableRes val currentDessert2ImageId: Int = dessertList[currentDessert2Index].imageId
)