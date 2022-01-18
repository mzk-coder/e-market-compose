package com.mzk.samplee_market.ui.screens

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.mzk.samplee_market.ui.theme.SampleEMarketTheme

@Composable
fun SuccessScreen(onClick : ()-> Unit){
    Success(onClick)
}

@Composable
fun Success(onClick : ()-> Unit){
    Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
        Button(onClick = {
            onClick()
        }) {
            Text(text = "DISMISS")
        }
    }
}

@Preview
@Composable
fun SuccessPreview(){
    SampleEMarketTheme {
        Success {

        }
    }
}