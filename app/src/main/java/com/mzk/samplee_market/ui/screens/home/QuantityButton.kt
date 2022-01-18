package com.mzk.samplee_market.ui.screens.home


import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.mzk.samplee_market.R
import com.mzk.samplee_market.ui.theme.*


@Composable
fun QuantityButton(modifier: Modifier, count: Int = 0, onQuantityChange: (Int) -> Unit) {

    var quantity by remember { mutableStateOf(count) }

    Row(
        modifier = modifier
            .height(Huge)
            .background(
                color = MaterialTheme.colors.buttonBackGroundColor,
                shape = RoundedCornerShape(Small)
            ), verticalAlignment = Alignment.CenterVertically
    ) {

        val buttonTextStyle = TextStyle(
            fontWeight = FontWeight.Bold,
            color = MaterialTheme.colors.buttonContentColor,
        )

        if (quantity > 0) {
            val iconButtonModifier = Modifier.weight(0.3f)
            val iconModifier = Modifier.size(18.dp)
            IconButton(modifier = iconButtonModifier, onClick = {

                quantity -= 1
                onQuantityChange(quantity)
            }) {
                Icon(
                    modifier = iconModifier,
                    painter = painterResource(id = R.drawable.ic_baseline_remove),
                    contentDescription = "Minus",
                    tint = MaterialTheme.colors.buttonContentColor
                )
            }

            Text(text = "$quantity", style = buttonTextStyle)

            IconButton(modifier = iconButtonModifier, onClick = {
                quantity += 1
                onQuantityChange(quantity)

            }) {
                Icon(
                    modifier = iconModifier,
                    painter = painterResource(id = R.drawable.ic_baseline_add),
                    contentDescription = "Add",
                    tint = MaterialTheme.colors.buttonContentColor
                )
            }
        } else {
            TextButton(modifier = Modifier.fillMaxWidth(), onClick = {
                quantity += 1
                onQuantityChange(quantity)
            }) {
                Text(text = "ADD", style = buttonTextStyle)
            }
        }

    }

}


@Preview
@Composable
fun ButtonPreview() {

    QuantityButton(
        modifier = Modifier
            .fillMaxWidth()
            .background(color = MaterialTheme.colors.buttonBackGroundColor), count = 0
    ) { a ->

    }
}