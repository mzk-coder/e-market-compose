package com.mzk.samplee_market.ui.screens.home

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Star
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.mzk.samplee_market.data.models.StoreInfo
import com.mzk.samplee_market.ui.theme.*
import com.mzk.samplee_market.utils.Utility.textSizeDensity


@Composable
fun StoreInformation(storeInfo: StoreInfo) {
    Row(modifier = Modifier
        .fillMaxWidth()
        .padding(Huge),
        horizontalArrangement = Arrangement.Center,
        verticalAlignment = Alignment.CenterVertically
    ) {

        Text(text = storeInfo.name, style = TextStyle(
            fontSize = textSizeDensity(24)
        ))
        Spacer(modifier = Modifier.width(Medium))
        RatingView(rating = storeInfo.rating)

    }
}

@Composable
fun RatingView(rating: Double){
    val bColor = when (rating) {
        in 3.5..5.0 -> {
            SecondaryDark
        }
        in 2.5..3.5 -> {
            Yellow
        }
        else -> {
            Red
        }
    }


    Row(modifier = Modifier
        .background(
            color = bColor,
            shape = RoundedCornerShape(Small)
        )
        .padding(horizontal = Small, vertical = Small)
    ) {
        Text(text = "$rating", fontSize = textSizeDensity(size = 12), color = Color.White)
        Icon(
            imageVector = Icons.Default.Star,
            contentDescription = null,
            modifier = Modifier.size(16.dp),
            tint = Color.White
        )
    }
}



@Preview
@Composable
fun StoreInformationPreview() {
    SampleEMarketTheme {
        StoreInformation(storeInfo = StoreInfo())
    }
}

