package com.mzk.samplee_market.navigations


const val ADDRESS = "address"

sealed class Screens(val route : String){

    object HomeScreen : Screens(route = "homeScreen")
    object SummaryScreen : Screens(route = "summaryScreen")
    object SuccessScreen : Screens(route = "successScreen")


}