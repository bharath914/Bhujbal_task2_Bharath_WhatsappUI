package com.bharath.bharath_instagram.domain

import com.bharath.bharath_instagram.data.entity.product.Data
import com.bharath.bharath_instagram.data.entity.product.ProductDetails

interface RepoInterface {

    suspend fun getProductDetails(): ProductDetails
}