package com.bharath.bharath_instagram.di

import com.bharath.bharath_instagram.data.remote.ProductApi
import com.bharath.bharath_instagram.data.remote.Repository
import com.bharath.bharath_instagram.domain.RepoInterface
import com.bharath.bharath_instagram.other.DataCons
import com.bharath.bharath_instagram.usecases.GetProductListUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideRetrofitInstance(): ProductApi = Retrofit.Builder()
        .baseUrl(DataCons.BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())

        .build()
        .create(ProductApi::class.java)

    @Singleton
    @Provides
    fun provideRepoInterface(productApi: ProductApi): RepoInterface = Repository(productApi)


    @Provides
    @Singleton
    fun provideProductListUseCase(repository: Repository) = GetProductListUseCase(repository)

}