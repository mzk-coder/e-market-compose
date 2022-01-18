package com.mzk.samplee_market.ui.screens.summary

import androidx.compose.foundation.layout.*
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import com.mzk.samplee_market.data.models.Product
import com.mzk.samplee_market.ui.theme.Large
import com.mzk.samplee_market.utils.Utility

@Composable
fun SelectedProduct(product: Product) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(Large)
    ) {
        Text(text = product.name, style = TextStyle(
            fontSize = Utility.textSizeDensity(16),
            fontWeight = FontWeight.Bold,
        ))

        Box(modifier = Modifier.fillMaxWidth()) {
            val textStyle =  TextStyle(
                fontSize = Utility.textSizeDensity(12),
            )
            Text(
                modifier = Modifier.align(Alignment.CenterStart),
                text = "Rs.${product.price} x ${product.quantity}",
                style = textStyle
            )
            Text(
                modifier = Modifier.align(Alignment.CenterEnd),
                text = "Rs.${product.price * product.quantity}",
                style = textStyle
            )
        }
    }

}


@Preview
@Composable
fun SelectedProductPreview() {
    SelectedProduct(product = Product())
}