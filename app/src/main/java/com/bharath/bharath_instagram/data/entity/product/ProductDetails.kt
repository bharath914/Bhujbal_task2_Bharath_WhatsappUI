package com.bharath.bharath_instagram.data.entity.product

data class ProductDetails(
    val data: List<Data> = emptyList(),
    val message: String="",
    val status: String =""
)