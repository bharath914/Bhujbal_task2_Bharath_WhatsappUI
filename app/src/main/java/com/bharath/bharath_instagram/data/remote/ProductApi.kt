package com.bharath.bharath_instagram.data.remote

import com.bharath.bharath_instagram.data.entity.product.ProductDetails
import com.bharath.bharath_instagram.other.DataCons
import retrofit2.http.GET

interface ProductApi {

    @GET(DataCons.getRequest)
    suspend fun getProductData(): ProductDetails

}