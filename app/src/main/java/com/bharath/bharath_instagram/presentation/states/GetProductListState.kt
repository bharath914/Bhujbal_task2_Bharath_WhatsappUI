package com.bharath.bharath_instagram.presentation.states

import com.bharath.bharath_instagram.data.entity.product.ProductDetails

data class GetProductListState(
    val isLoading: Boolean = false,
    val details: ProductDetails = ProductDetails(),
    val message: String = "",
)

