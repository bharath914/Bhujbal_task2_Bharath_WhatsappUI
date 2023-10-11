package com.bharath.bharath_instagram.presentation.states

import com.bharath.bharath_instagram.data.entity.product.Data
import com.bharath.bharath_instagram.data.entity.product.ProductDetails

data class GetProductListState(
    val isLoading: Boolean = false,
    val data: ProductDetails = ProductDetails(),
    val message: String = "",
)

