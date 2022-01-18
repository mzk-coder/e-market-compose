package com.mzk.samplee_market.repository


import com.mzk.samplee_market.data.models.OrderRequest
import com.mzk.samplee_market.data.models.OrderValue
import com.mzk.samplee_market.data.models.Product
import com.mzk.samplee_market.data.models.StoreInfo
import com.mzk.samplee_market.data.repository.EMarketRepository
import com.mzk.samplee_market.utils.RequestState
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow

class DummyRepository : EMarketRepository{

    private val remoteProductList = MutableStateFlow<List<Product>>(arrayListOf())
    private val productList = mutableListOf<Product>()

    private val orderValue = MutableStateFlow(OrderValue(0,0))


    private var isNetworkError = false

    fun setNetworkError(error: Boolean){
        isNetworkError = error
    }


    override suspend fun getStoreInformation(): RequestState<StoreInfo> {
        return if (isNetworkError){
            RequestState.Error(Throwable("Something went wrong"))
        }else{
            RequestState.Success(StoreInfo())
        }
    }

    override suspend fun getProducts(): RequestState<List<Product>> {
        return if (isNetworkError){
            RequestState.Error(Throwable("Something went wrong"))
        }else{
            RequestState.Success(listOf())
        }
    }

    override suspend fun placeOrder(orderRequest: OrderRequest): RequestState<Boolean> {
        return when {
            isNetworkError -> {
                RequestState.Error(Throwable("Something went wrong"))
            }
            orderRequest.delivery_address.isEmpty() -> {
                RequestState.Error(Throwable("delivery address empty"))
            }
            orderRequest.products.isEmpty() -> {
                RequestState.Error(Throwable("Empty order list"))
            }
            else -> {
                RequestState.Success(true)
            }
        }
    }

    override val getAllProduct: Flow<List<Product>>
        get() = remoteProductList

    override val getOrderValue: Flow<OrderValue>
        get() = orderValue

    override fun getSelectedProduct(productId: Int): Flow<Product?> {
        return MutableStateFlow(productList.first { it.id == productId })
    }

    override suspend fun addProduct(product: Product) {
        productList.add(product)
    }

    override suspend fun deleteProduct(product: Product) {
        productList.remove(product)
    }

    override suspend fun deleteAll() {
        productList.clear()
    }
}