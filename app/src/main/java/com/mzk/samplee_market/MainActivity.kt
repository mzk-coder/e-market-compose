package com.mzk.samplee_market

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import com.mzk.samplee_market.navigations.SetUpNavigation
import com.mzk.samplee_market.ui.theme.SampleEMarketTheme
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class MainActivity : ComponentActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            SampleEMarketTheme(darkTheme = false) {
                Surface(color = MaterialTheme.colors.background) {
                    SetUpNavigation()
                }
            }
        }
    }
}

