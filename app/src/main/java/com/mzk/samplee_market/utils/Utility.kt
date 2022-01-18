package com.mzk.samplee_market.utils

import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.sp

object Utility {

    @Composable
    fun textSizeDensity(size : Int): TextUnit {
        val unit = with(LocalDensity.current) {
            (size / fontScale).sp
        }
        return unit
    }


}