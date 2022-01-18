package com.mzk.samplee_market.data.repository

import com.mzk.samplee_market.data.models.OrderRequest
import com.mzk.samplee_market.data.models.OrderValue
import com.mzk.samplee_market.data.models.Product
import com.mzk.samplee_market.data.models.StoreInfo
import com.mzk.samplee_market.utils.RequestState
import kotlinx.coroutines.flow.Flow

interface EMarketRepository {

    suspend fun getStoreInformation(): RequestState<StoreInfo>

    suspend fun getProducts(): RequestState<List<Product>>

    suspend fun placeOrder(orderRequest: OrderRequest): RequestState<Boolean>

    val getAllProduct: Flow<List<Product>>

    val getOrderValue: Flow<OrderValue>

    fun getSelectedProduct(productId: Int): Flow<Product?>

    suspend fun addProduct(product: Product)

    suspend fun deleteProduct(product: Product)

    suspend fun deleteAll()

}