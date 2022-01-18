package com.mzk.samplee_market.data.models

data class StoreInfo(
    val name: String,
    val openingTime: String,
    val closingTime: String,
    val rating: Double
){
    constructor() : this("The Coffee Shop", "08:00 AM", "10:00 PM", 4.5)
}