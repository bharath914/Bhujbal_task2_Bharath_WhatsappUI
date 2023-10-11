package com.bharath.bharath_instagram.usecases

import com.bharath.bharath_instagram.data.entity.product.Data
import com.bharath.bharath_instagram.data.entity.product.ProductDetails
import com.bharath.bharath_instagram.data.remote.Repository
import com.bharath.bharath_instagram.data.resource.Resource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import okio.IOException
import retrofit2.HttpException
import javax.inject.Inject

class GetProductListUseCase @Inject constructor(
    private val repo: Repository,

    ) {

    operator fun invoke(): Flow<Resource<ProductDetails>> = flow {

        try {
            emit(Resource.Loading())
            val data = repo.getProductDetails()
            emit(Resource.Success(data))


        } catch (e: HttpException) {
            emit(Resource.Error(e.localizedMessage ?: "Unexpected error"))
        } catch (e: IOException) {
            emit(Resource.Error("Servers Are Busy Right Now"))
        }

    }
}