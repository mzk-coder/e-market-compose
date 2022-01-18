package com.mzk.samplee_market.ui.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mzk.samplee_market.data.models.HomeData
import com.mzk.samplee_market.data.models.OrderRequest
import com.mzk.samplee_market.data.models.OrderValue
import com.mzk.samplee_market.data.models.Product
import com.mzk.samplee_market.data.repository.DefaultEMarketRepository
import com.mzk.samplee_market.data.repository.EMarketRepository
import com.mzk.samplee_market.utils.RequestState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class EMarketViewModel @Inject constructor(
    private val eMarketRepository: EMarketRepository,
) : ViewModel() {

    private val _homeData = MutableStateFlow<RequestState<HomeData>>(RequestState.Idle)
    val homeData: StateFlow<RequestState<HomeData>> = _homeData


    private val _orderRequest = MutableStateFlow<RequestState<Boolean>>(RequestState.Idle)
    val orderRequest: StateFlow<RequestState<Boolean>> = _orderRequest

    private val _orderList = MutableStateFlow<List<Product>>(arrayListOf())
    val orderList : StateFlow<List<Product>> = _orderList

    private val _orderValue = MutableStateFlow(OrderValue(0,0))
    val orderValue : StateFlow<OrderValue> = _orderValue


    fun updateOrder(product : Product){
        viewModelScope.launch {
            if (product.quantity>0) {
                eMarketRepository.addProduct(product = product)
            }else{
                eMarketRepository.deleteProduct(product = product)
            }
        }
        getOrderValue()
    }

    fun clearDatabase(){
        viewModelScope.launch {
                eMarketRepository.deleteAll()
        }
    }

    fun resetOrderRequest(){
        _orderRequest.value = RequestState.Idle
    }

    fun getAllOrder(){
        viewModelScope.launch {
            eMarketRepository.getAllProduct.collect {
                _orderList.value = it
            }
        }
    }

    fun getOrderValue(){
        viewModelScope.launch {
            eMarketRepository.getOrderValue.collect {
                _orderValue.value = it
            }
        }
    }

    fun requestHomeScreenData() {
        _homeData.value = RequestState.Loading
        viewModelScope.launch {
            val storeInfoRequest = eMarketRepository.getStoreInformation()

            if (storeInfoRequest is RequestState.Success) {
                val productsRequest = eMarketRepository.getProducts()
                if (productsRequest is RequestState.Success) {
                    val products = productsRequest.data.mapIndexed { index, product ->
                        val dbProduct = eMarketRepository.getSelectedProduct(index).first()

                        val mProduct = product.apply {
                            id = index
                            quantity = dbProduct?.quantity?:0
                        }
                        mProduct
                    }
                    val homeData = HomeData(storeInfoRequest.data, products)
                    _homeData.value = RequestState.Success(homeData)

                } else if (productsRequest is RequestState.Error) {
                    _homeData.value = RequestState.Error(productsRequest.error)
                }
            } else if (storeInfoRequest is RequestState.Error) {
                _homeData.value = RequestState.Error(storeInfoRequest.error)
            }
        }
    }

    fun placeOrder(orderRequest: OrderRequest) {
        _orderRequest.value = RequestState.Loading
        viewModelScope.launch {
            _orderRequest.value = eMarketRepository.placeOrder(orderRequest = orderRequest)
        }
    }


}