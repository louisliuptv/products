package com.example.baseandroid.ui

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.baseandroid.data.Product


@Composable
fun ProductsScreen(
    viewModel: ProductViewModel = viewModel()
) {
    val state by viewModel.state.collectAsStateWithLifecycle()

    Scaffold(

    ) { paddingValues ->

        Box(
            modifier = Modifier.fillMaxSize().padding(paddingValues)
        ) {
            when(state.loadingState) {
                LoadingState.LOADING -> {
                    Box(
                        modifier = Modifier.fillMaxSize(),
                        contentAlignment = Alignment.Center
                    ) {
                        Text("Loading...")
                    }
                }
                LoadingState.SUCCESS -> {
                    LazyColumn() {

                        items(items = state.products, key = {it.id}) {
                            ProductView(it)
                        }

                    }
                }
                LoadingState.ERROR -> {

                }
                else -> {}
            }


        }

    }

}

@Composable
private fun ProductView(
    product: Product
) {
    Column(
        modifier = Modifier.fillMaxSize().padding(16.dp)
    ) {
        Row() {
            Text("Title")
            Text(product.title)
        }

        Row() {
            Text("Price")
            Text(product.price.toString())
        }
        Row() {
            Text("Aviability")
            Text(product.availabilityStatus)
        }
    }

}