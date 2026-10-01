package com.example.baseandroid.data

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.withContext
import java.math.BigDecimal

class ProductRespotory {


    suspend fun getProducts(): Result<List<Product>> = withContext(Dispatchers.IO) {
        delay(2000)
        Result.success(listOf(
            Product(
                1,
                "Essence Mascara Lash Princess 1",
                "beauty",
                BigDecimal("9.99"),
                "In Stock"
            ),
            Product(
                2,
                "Essence Mascara Lash Princess 2",
                "beauty",
                BigDecimal("9.99"),
                "In Stock"
            ),
            Product(
                3,
                "Essence Mascara Lash Princess 3",
                "beauty",
                BigDecimal("9.99"),
                "In Stock"
            ),
            Product(
                4,
                "Essence Mascara Lash Princess 4",
                "beauty",
                BigDecimal("9.99"),
                "In Stock"
            )
        ))

    }
}