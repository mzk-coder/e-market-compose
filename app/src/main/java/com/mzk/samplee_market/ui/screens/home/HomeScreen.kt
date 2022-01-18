package com.mzk.samplee_market.ui.screens.home

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.Divider
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.mzk.samplee_market.data.models.HomeData
import com.mzk.samplee_market.data.models.OrderValue
import com.mzk.samplee_market.data.models.Product
import com.mzk.samplee_market.ui.theme.Large
import com.mzk.samplee_market.ui.theme.Medium
import com.mzk.samplee_market.ui.theme.SampleEMarketTheme
import com.mzk.samplee_market.ui.viewmodels.EMarketViewModel
import com.mzk.samplee_market.utils.RequestState


@Composable
fun HomeScreen(eMarketViewModel: EMarketViewModel, onClick: () -> Unit) {


    val homeData by eMarketViewModel.homeData.collectAsState()



    LaunchedEffect(key1 = null) {
        eMarketViewModel.requestHomeScreenData()
        eMarketViewModel.getOrderValue()
    }

    val orderValue by eMarketViewModel.orderValue.collectAsState()



    when (homeData) {
        is RequestState.Loading -> {
            Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                CircularProgressIndicator()
            }

        }
        is RequestState.Success -> {
            Home(
                homeData = (homeData as RequestState.Success<HomeData>).data,
                orderValue,
                onClick
            ) { prod ->

                eMarketViewModel.updateOrder(product = prod)
                eMarketViewModel.getOrderValue()
            }
        }
        else -> {
            Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                Text(text = "Something went wrong")
            }
        }
    }

}

@Composable
fun Home(
    homeData: HomeData?,
    orderValue: OrderValue,
    onClick: () -> Unit,
    onProductClick: (Product) -> Unit
) {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .padding(bottom = Large)
    ) {
        Column(
            modifier = Modifier.fillMaxWidth(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            homeData?.let { home ->
                StoreInformation(storeInfo = home.storeInfo)
                LazyColumn {
                    itemsIndexed(
                        items = home.productList,
                    ) { index, product ->
                        ProductView(product = product) { count ->
                            onProductClick(product.apply {
                                quantity = count
                            })
                        }
                        //SelectedProduct(product = product)
                        if (index<home.productList.lastIndex){
                            Divider(thickness = 1.dp)
                        }
                    }
                }

            }

        }
        if (orderValue.count > 0) {
            BottomOrderValue(
                modifier = Modifier
                    .align(Alignment.BottomCenter)
                    .fillMaxWidth(0.9f)
                    .background(
                        color = MaterialTheme.colors.secondary,
                        shape = RoundedCornerShape(Medium)
                    )
                    .padding(horizontal = Large, vertical = Medium)
                    .clickable { onClick() },
                orderValue = orderValue
            )
        }


    }
}


@Preview
@Composable
fun HomePreview() {
    SampleEMarketTheme {
        Home(homeData = null, OrderValue(9, 0), {}, {})
    }
}