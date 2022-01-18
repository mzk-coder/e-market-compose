package com.mzk.samplee_market.network

import com.mzk.samplee_market.data.models.OrderRequest
import com.mzk.samplee_market.data.models.Product
import com.mzk.samplee_market.data.models.StoreInfo
import com.mzk.samplee_market.utils.Constants.GET_STORE_INFO
import com.mzk.samplee_market.utils.Constants.GET_PRODUCTS
import com.mzk.samplee_market.utils.Constants.PLACE_ORDER
import okhttp3.ResponseBody
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST

interface ApiService {

    @GET(GET_STORE_INFO)
    suspend fun getStoreInfo(): Response<StoreInfo?>

    @GET(GET_PRODUCTS)
    suspend fun getProducts(): Response<List<Product>?>

    @POST(PLACE_ORDER)
    suspend fun placeOrder(@Body orderRequest: OrderRequest): Response<ResponseBody>

}