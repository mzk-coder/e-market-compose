package com.mzk.samplee_market.ui.screens.home

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowForward
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.mzk.samplee_market.data.models.OrderValue
import com.mzk.samplee_market.ui.theme.Medium

@Composable
fun BottomOrderValue(modifier: Modifier, orderValue: OrderValue) {
    Row(
        modifier = modifier,
        verticalAlignment = Alignment.CenterVertically,

        ) {
        Text(text = "Complete Order", modifier = Modifier.weight(1f))
        Column() {
            Text(text = "${orderValue.count}(Items)")
            Text(text = "Rs.${orderValue.total}")
        }
        Spacer(modifier = Modifier.width(Medium))
        Icon(imageVector = Icons.Default.ArrowForward, contentDescription = "Next")

    }
}