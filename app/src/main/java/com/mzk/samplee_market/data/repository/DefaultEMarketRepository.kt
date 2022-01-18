package com.mzk.samplee_market.data.repository

import com.mzk.samplee_market.data.models.OrderRequest
import com.mzk.samplee_market.data.models.OrderValue
import com.mzk.samplee_market.data.models.Product
import com.mzk.samplee_market.data.models.StoreInfo
import com.mzk.samplee_market.db.OrderDao
import com.mzk.samplee_market.network.ApiService
import com.mzk.samplee_market.utils.RequestState
import dagger.hilt.android.scopes.ViewModelScoped
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject


@ViewModelScoped
class DefaultEMarketRepository @Inject constructor(private val apiService: ApiService, private val orderDao: OrderDao) : EMarketRepository{



    override suspend fun getStoreInformation(): RequestState<StoreInfo> {
        val request = apiService.getStoreInfo()
        return if (request.code() == 200) {
            RequestState.Success(request.body()!!)
        } else {
            RequestState.Error(Throwable(request.message()))
        }
    }

    override suspend fun getProducts() : RequestState<List<Product>> {
        val request = apiService.getProducts()
        return if (request.code()==200){
            RequestState.Success(request.body()!!)
        }else{
            RequestState.Error(Throwable(request.message()))
        }
    }

    override suspend fun placeOrder(orderRequest: OrderRequest) : RequestState<Boolean> {
        val request = apiService.placeOrder(orderRequest = orderRequest)
        return if (request.code()==201){
            RequestState.Success(true)
        }else{
            RequestState.Error(Throwable(request.message()))
        }
    }


    override val getAllProduct : Flow<List<Product>> = orderDao.getAllProduct()

    override val getOrderValue : Flow<OrderValue> = orderDao.getOrderValue()

    override fun getSelectedProduct(productId: Int) : Flow<Product?> {
        return orderDao.getSelectedProduct(productId)
    }

    override suspend fun addProduct(product : Product){
        orderDao.addProduct(product)
    }

    override suspend fun deleteProduct(product : Product){
        orderDao.deleteProduct(product)
    }

    override suspend fun deleteAll(){
        orderDao.deleteAll()
    }


}