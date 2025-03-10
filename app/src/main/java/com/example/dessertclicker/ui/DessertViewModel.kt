package com.example.dessertclicker.ui

import androidx.lifecycle.ViewModel
import com.example.dessertclicker.data.DessertUIState
import com.example.dessertclicker.data.Datasource.dessertList
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

/**
 * ViewModel quản lý trạng thái của ứng dụng Dessert Clicker.
 * Sử dụng `StateFlow` để cung cấp trạng thái UI một cách phản ứng (reactive).
 */
class DessertViewModel : ViewModel() {

    // `_dessertUIState` là một `MutableStateFlow`, giúp quản lý trạng thái của UI.
    private val _dessertUIState = MutableStateFlow(DessertUIState())

    // `dessertUIState` là `StateFlow` chỉ đọc, dùng để cung cấp dữ liệu ra ngoài UI.
    val dessertUIState: StateFlow<DessertUIState> = _dessertUIState.asStateFlow()

    /**
     * Xử lý khi người dùng click vào hình ảnh Dessert 1.
     * - Cập nhật doanh thu (`revenue`) bằng cách cộng giá của Dessert 1.
     * - Tăng tổng số dessert đã bán (`dessertsSold`).
     * - Có thể cập nhật loại dessert mới khi đạt đủ số lượng bán.
     */
    fun onDessert1Clicked() {
        _dessertUIState.update { currentState ->
            val newRevenue = currentState.revenue + currentState.currentDessert1Price
            val newDessertsSold = currentState.dessertsSold + 1

            // Kiểm tra xem có cần thay đổi loại dessert hay không (nếu cần, bỏ comment đoạn dưới)
            // val newDessertIndex = determineDessertIndex(newDessertsSold)

            currentState.copy(
                revenue = newRevenue,
                dessertsSold = newDessertsSold
                // Nếu muốn cập nhật dessert mới, bỏ comment đoạn sau:
                // currentDessert1Index = newDessertIndex,
                // currentDessert1Price = dessertList[newDessertIndex].price,
                // currentDessert1ImageId = dessertList[newDessertIndex].imageId
            )
        }
    }

    /**
     * Xử lý khi người dùng click vào hình ảnh Dessert 2.
     * - Cập nhật doanh thu (`revenue`) bằng cách cộng giá của Dessert 2.
     * - Tăng tổng số dessert đã bán (`dessertsSold`).
     * - Có thể cập nhật loại dessert mới khi đạt đủ số lượng bán.
     */
    fun onDessert2Clicked() {
        _dessertUIState.update { currentState ->
            val newRevenue = currentState.revenue + currentState.currentDessert2Price
            val newDessertsSold = currentState.dessertsSold + 1

            // Kiểm tra xem có cần thay đổi loại dessert hay không (nếu cần, bỏ comment đoạn dưới)
            // val newDessertIndex = determineDessertIndex(newDessertsSold)

            currentState.copy(
                revenue = newRevenue,
                dessertsSold = newDessertsSold
                // Nếu muốn cập nhật dessert mới, bỏ comment đoạn sau:
                // currentDessert2Index = newDessertIndex,
                // currentDessert2Price = dessertList[newDessertIndex].price,
                // currentDessert2ImageId = dessertList[newDessertIndex].imageId
            )
        }
    }

    /**
     * Xác định loại dessert nào sẽ hiển thị dựa trên số lượng đã bán (`dessertsSold`).
     * - Duyệt qua danh sách `dessertList` để tìm dessert phù hợp.
     * - Danh sách `dessertList` được sắp xếp theo `startProductionAmount`.
     * - Khi số lượng bán đạt đến mức của dessert tiếp theo, nó sẽ được chọn.
     */
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