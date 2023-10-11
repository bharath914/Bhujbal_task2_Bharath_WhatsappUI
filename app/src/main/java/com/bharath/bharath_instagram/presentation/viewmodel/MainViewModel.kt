package com.bharath.bharath_instagram.presentation.viewmodel

import android.content.Context
import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.bharath.bharath_instagram.data.entity.product.GroupData
import com.bharath.bharath_instagram.data.entity.product.OrderDetail
import com.bharath.bharath_instagram.data.entity.product.ProductDetails
import com.bharath.bharath_instagram.data.resource.Resource
import com.bharath.bharath_instagram.presentation.states.GetProductListState
import com.bharath.bharath_instagram.usecases.GetProductListUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val getProductListUseCase: GetProductListUseCase,
    @ApplicationContext val context: Context,
) : ViewModel() {

    private val _groupData = MutableStateFlow(emptyList<GroupData>())
    val groupData: StateFlow<List<GroupData>> = _groupData

    private val _allOrders = MutableStateFlow(emptyList<OrderDetail>())
    val allOrders: StateFlow<List<OrderDetail>> = _allOrders

    private val _productList = MutableStateFlow(GetProductListState())
    val productList: StateFlow<GetProductListState> get() = _productList


    init {
        getProductList()
    }

    private fun getProductList() {
        getProductListUseCase().onEach { result ->
            when (result) {
                is Resource.Success -> {
                    _productList.tryEmit(
                        GetProductListState(
                            isLoading = true
                        )
                    )
                    val G_list = mutableListOf<GroupData>()
                    result.data?.data?.forEachIndexed { index, data ->
                        data.group_data.forEach { group ->
                            G_list.add(group)

                        }
                    }
                    _groupData.tryEmit(G_list)
                    val all_Orders = mutableListOf<OrderDetail>()
                    G_list.forEachIndexed { index, groupData ->

                        groupData.order_detail.forEach {
                            all_Orders.add(it)
                        }
                    }
                    _allOrders.tryEmit(all_Orders)
                    all_Orders.forEach {
                        Log.d("List", "getProductList: ${it.name}")
                        Log.d("List", "Item Name : ${it.product_name}")
                    }
                    _productList.tryEmit(
                        GetProductListState(
                            data = result.data ?: ProductDetails()
                        )
                    )
                }

                is Resource.Error -> {
                    _productList.tryEmit(
                        GetProductListState(
                            message = result.message ?: "Unexpected Error"
                        )
                    )

                }

                is Resource.Loading -> {
                    _productList.tryEmit(
                        GetProductListState(isLoading = true)
                    )
                }
            }
        }.launchIn(viewModelScope)
    }

}
