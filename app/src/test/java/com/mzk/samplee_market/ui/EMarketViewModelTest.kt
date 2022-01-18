package com.mzk.samplee_market.ui


import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.google.common.truth.Truth.assertThat
import com.mzk.samplee_market.CoroutineRule
import com.mzk.samplee_market.data.models.OrderRequest
import com.mzk.samplee_market.data.models.Product
import com.mzk.samplee_market.repository.DummyRepository
import com.mzk.samplee_market.ui.viewmodels.EMarketViewModel
import com.mzk.samplee_market.utils.RequestState
import kotlinx.coroutines.ExperimentalCoroutinesApi
import org.junit.Before
import org.junit.Rule
import org.junit.Test

@ExperimentalCoroutinesApi
class EMarketViewModelTest {


    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    @get:Rule
    var coroutineRule = CoroutineRule()

    private lateinit var eMarketViewModel: EMarketViewModel

    @Before
    fun setup() {
        eMarketViewModel = EMarketViewModel(DummyRepository())
    }


    @Test
    fun `place order with empty address`() {
        val orderRequest = OrderRequest(listOf(Product()), "")
        eMarketViewModel.placeOrder(orderRequest)

        val response = eMarketViewModel.orderRequest.value


        assertThat(response is RequestState.Error).isTrue()
    }

    @Test
    fun `place order with empty product`() {
        val orderRequest = OrderRequest(arrayListOf(), "")
        eMarketViewModel.placeOrder(orderRequest)

        val response = eMarketViewModel.orderRequest.value

        assertThat(response is RequestState.Error).isTrue()
    }

    @Test
    fun `place order with required parameters`(){
        val orderRequest = OrderRequest(listOf(Product()), "address")
        eMarketViewModel.placeOrder(orderRequest)

        val response = eMarketViewModel.orderRequest.value

        assertThat(response is RequestState.Success).isTrue()
    }


}