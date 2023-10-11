package com.bharath.bharath_instagram.data.remote

import com.bharath.bharath_instagram.data.entity.product.Data
import com.bharath.bharath_instagram.data.entity.product.ProductDetails
import com.bharath.bharath_instagram.domain.RepoInterface
import javax.inject.Inject

class Repository @Inject constructor(
    private val productApi: ProductApi,
) : RepoInterface {
    override suspend fun getProductDetails(): ProductDetails {
        return productApi.getProductData()
    }
}