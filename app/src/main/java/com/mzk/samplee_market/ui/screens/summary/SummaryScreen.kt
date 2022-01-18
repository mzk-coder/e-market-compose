package com.mzk.samplee_market.ui.screens.summary

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardCapitalization
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.mzk.samplee_market.data.models.OrderRequest
import com.mzk.samplee_market.data.models.OrderValue
import com.mzk.samplee_market.data.models.Product
import com.mzk.samplee_market.ui.theme.Large
import com.mzk.samplee_market.ui.viewmodels.EMarketViewModel
import com.mzk.samplee_market.utils.RequestState
import com.mzk.samplee_market.utils.Utility.textSizeDensity


@Composable
fun SummaryScreen(eMarketViewModel: EMarketViewModel, onClick: () -> Unit) {


    val orderList by eMarketViewModel.orderList.collectAsState()

    val orderValue by eMarketViewModel.orderValue.collectAsState()

    val orderRequestStatus by eMarketViewModel.orderRequest.collectAsState()

    LaunchedEffect(key1 = orderRequestStatus) {
        eMarketViewModel.getAllOrder()
        eMarketViewModel.getOrderValue()

    }

    if (orderRequestStatus is RequestState.Success) {
        eMarketViewModel.clearDatabase()
        OrderSuccess(onClick)
    } else {
        Summary(orderList, orderValue, orderRequestStatus is RequestState.Loading) { add ->
            if (add.isNotEmpty()) {
                val orderRequest = OrderRequest(orderList, add)
                eMarketViewModel.placeOrder(orderRequest = orderRequest)
            }
        }
    }


}

@Composable
fun OrderSuccess(onClick : ()-> Unit){
    Column(modifier = Modifier.fillMaxSize(), horizontalAlignment = Alignment.CenterHorizontally, verticalArrangement = Arrangement.Center) {
         
        Text(
            
            text = "Order Placed Successfully",
            style = TextStyle(
                fontSize = textSizeDensity(20),
                fontWeight = FontWeight.Bold,
                color = MaterialTheme.colors.secondary

            )
        )
        Spacer(modifier = Modifier.height(Large))
        Button(onClick = {
            onClick()
        }) {
            Text(text = "DISMISS")
        }
    }
}



@Composable
fun Summary(
    orderList: List<Product>,
    orderValue: OrderValue,
    posting: Boolean,
    onClick: (String) -> Unit
) {
    Column(modifier = Modifier.fillMaxSize(), horizontalAlignment = Alignment.CenterHorizontally) {
        LazyColumn {
            itemsIndexed(orderList) { _, item ->
                SelectedProduct(product = item)
                Divider(thickness = 1.dp)

            }
        }
        Spacer(modifier = Modifier.height(Large))

        Box(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = Large)
        ) {
            val textStyle = TextStyle(
                fontSize = textSizeDensity(16),
                fontWeight = FontWeight.Bold

            )
            Text(
                modifier = Modifier.align(Alignment.CenterStart),
                text = "Total Price",
                style = textStyle
            )
            Text(
                modifier = Modifier.align(Alignment.CenterEnd),
                text = "Rs.${orderValue.total}",
                style = textStyle
            )
        }
        var address by remember { mutableStateOf("") }

        var isError by remember { mutableStateOf(false) }

        Spacer(modifier = Modifier.height(Large))

        OutlinedTextField(
            modifier = Modifier.fillMaxWidth(0.9f),
            value = address, onValueChange = {
                isError = it.isEmpty()
                address = it

            },
            label = { Text(text = "Delivery Address", fontSize = textSizeDensity(12)) },
            keyboardOptions = KeyboardOptions(
                keyboardType = KeyboardType.Text,
                capitalization = KeyboardCapitalization.Words,
                imeAction = ImeAction.Done
            ),
            keyboardActions = KeyboardActions(
                onDone = {
                    isError = address.isEmpty()
                    if (address.isNotEmpty()) {
                        onClick(address)
                    }
                }
            ),
            isError = isError
        )
        if (isError) {
            Text(
                text = "Please enter address",
                color = MaterialTheme.colors.error,
                style = MaterialTheme.typography.caption,
                modifier = Modifier.fillMaxWidth(0.9f),
            )
        }

        Spacer(modifier = Modifier.height(Large))

        if (posting) {
            LinearProgressIndicator(
                modifier = Modifier.fillMaxWidth(0.9f),
                color = MaterialTheme.colors.secondary
            )
        }

        Button(modifier = Modifier.fillMaxWidth(0.9f), onClick = {
            isError = address.isEmpty()
            if (address.isNotEmpty()) {
                onClick(address)
            }
        }, enabled = !posting) {
            Text(text = "PLACE ORDER")
        }
    }
}


@Preview
@Composable
fun SummaryPreview() {
    Summary(arrayListOf(), OrderValue(0, 0), false) {

    }
}
