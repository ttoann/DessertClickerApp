package com.example.dessertclicker.ui

import androidx.lifecycle.ViewModel
import com.example.dessertclicker.data.DessertUIState
import com.example.dessertclicker.data.Datasource.dessertList
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

class DessertViewModel : ViewModel() {

    private val _dessertUIState = MutableStateFlow(DessertUIState())
    val dessertUIState: StateFlow<DessertUIState> = _dessertUIState.asStateFlow()

    fun onDessert1Clicked() {
        _dessertUIState.update { currentState ->
            val newRevenue = currentState.revenue + currentState.currentDessert1Price
            val newDessertsSold = currentState.dessertsSold + 1

            // Optionally change the dessert based on sales
            // val newDessertIndex = determineDessertIndex(currentState.dessertsSold + 1)

            currentState.copy(
                revenue = newRevenue,
                dessertsSold = newDessertsSold
                // Uncomment to update the dessert
                // currentDessert1Index = newDessertIndex,
                // currentDessert1Price = dessertList[newDessertIndex].price,
                // currentDessert1ImageId = dessertList[newDessertIndex].imageId
            )
        }
    }

    fun onDessert2Clicked() {
        _dessertUIState.update { currentState ->
            val newRevenue = currentState.revenue + currentState.currentDessert2Price
            val newDessertsSold = currentState.dessertsSold + 1

            // Optionally change the dessert based on sales
            // val newDessertIndex = determineDessertIndex(currentState.dessertsSold + 1)

            currentState.copy(
                revenue = newRevenue,
                dessertsSold = newDessertsSold
                // Uncomment to update the dessert
                // currentDessert2Index = newDessertIndex,
                // currentDessert2Price = dessertList[newDessertIndex].price,
                // currentDessert2ImageId = dessertList[newDessertIndex].imageId
            )
        }
    }

    private fun determineDessertIndex(dessertsSold: Int): Int {
        var dessertIndex = 0
        for (index in dessertList.indices) {
            if (dessertsSold >= dessertList[index].startProductionAmount) {
                dessertIndex = index
            } else {
                // The list of desserts is sorted by startProductionAmount. As you sell more
                // desserts, you'll start producing more expensive desserts as determined by
                // startProductionAmount. We know to break as soon as we see a dessert who's
                // "startProductionAmount" is greater than the amount sold.
                break
            }
        }
        return dessertIndex
    }
}