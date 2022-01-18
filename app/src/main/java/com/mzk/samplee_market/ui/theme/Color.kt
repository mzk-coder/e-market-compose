package com.mzk.samplee_market.ui.theme

import androidx.compose.material.Colors
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color



val Primary = Color(0xFF0267ee)
val PrimaryDark = Color(0xFF003eba)
val PrimaryLight = Color(0xFF6694ff)

val Secondary = Color(0xFF9ccc65)
val SecondaryDark = Color(0xFF6b9b37)


val Yellow = Color(0xFFB3A001)
val Red = Color(0xFFF44336)




val LightGray = Color(0xFFFFFFFF)
val DarkGray = Color(0xFF141414)

val Colors.buttonBackGroundColor: Color
    @Composable
    get() = if (isLight) PrimaryDark else SecondaryDark


val Colors.buttonContentColor: Color
    @Composable
    get() = if (isLight) LightGray else DarkGray


