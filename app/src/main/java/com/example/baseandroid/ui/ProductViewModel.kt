package com.example.baseandroid.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.baseandroid.data.Product
import com.example.baseandroid.data.ProductRespotory
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

enum class LoadingState {
    IDLE,
    LOADING,
    ERROR,
    SUCCESS
}

data class UiState(
    val loadingState: LoadingState = LoadingState.IDLE,
    val products: List<Product> = emptyList(),
    val errorMsg: String? = null
)


class ProductViewModel(
    val productRespotory: ProductRespotory = ProductRespotory()
) : ViewModel() {

    private val _state = MutableStateFlow(UiState())
    val state = _state.asStateFlow()

    init {
        getProducts()
    }

    fun getProducts() {
        viewModelScope.launch {

            _state.update {
                it.copy(loadingState = LoadingState.LOADING)
            }

            productRespotory.getProducts().fold(
                { result ->
                    _state.update {
                        it.copy(
                            loadingState = LoadingState.SUCCESS,
                            products = result
                        )
                    }
                },
                { error ->
                    _state.update {
                        it.copy(
                            loadingState = LoadingState.ERROR,
                            errorMsg = error.message
                        )
                    }
                }
            )
        }
    }
}