package com.mzk.samplee_market.ui.screens.home

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import com.mzk.samplee_market.R
import com.mzk.samplee_market.data.models.Product
import com.mzk.samplee_market.ui.theme.Medium
import com.mzk.samplee_market.ui.theme.SampleEMarketTheme
import com.mzk.samplee_market.ui.theme.Small
import com.mzk.samplee_market.utils.Utility.textSizeDensity
import com.mzk.samplee_market.utils.imageLoader


@Composable
fun ProductView(product: Product, onQuantityChange: (Int) -> Unit) {
    BoxWithConstraints() {
        val width = maxWidth
        val imageSize = width * 0.2f
        Row(modifier = Modifier
            .fillMaxWidth()
            .padding(Medium), verticalAlignment = Alignment.CenterVertically) {

            imageLoader(product.imageUrl).value?.let {
                Image(
                    bitmap = it.asImageBitmap(),
                    contentDescription = "ProductImage",
                    contentScale = ContentScale.Crop,
                    modifier = Modifier
                        .size(imageSize)
                        .clip(shape = RoundedCornerShape(Small)),

                )
            }

            Spacer(modifier = Modifier.width(Medium))
            Column(modifier = Modifier.weight(1f)) {
                Text(text = product.name, style = TextStyle(
                    fontSize = textSizeDensity(20),
                    fontWeight = FontWeight.Bold,
                ))
                Text(text = "Rs.${product.price}", style = TextStyle(
                    fontSize = textSizeDensity(14),
                    color = MaterialTheme.colors.primary,
                ))
            }
            QuantityButton(modifier = Modifier.width(width*0.25f)
                , count = product.quantity, onQuantityChange = onQuantityChange)

        }

    }

}

@Preview
@Composable
fun ProductPreviewView() {
    SampleEMarketTheme() {
        ProductView(product = Product()){}
    }
}