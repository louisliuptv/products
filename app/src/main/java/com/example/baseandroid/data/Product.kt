package com.example.baseandroid.data

import java.math.BigDecimal

data class Product(
    val id: Int,
    val title: String,
    val category: String,
    val price: BigDecimal,
    val availabilityStatus: String
)
