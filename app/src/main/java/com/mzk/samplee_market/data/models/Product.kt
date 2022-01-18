package com.mzk.samplee_market.data.models

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.mzk.samplee_market.utils.Constants.ORDER_TABLE

@Entity(tableName = ORDER_TABLE)
data class Product(
    @PrimaryKey(autoGenerate = false)
    var id : Int,
    val imageUrl: String,
    val name: String,
    val price: Int,
    var quantity : Int
){
    constructor() : this(-1,"text", "Product", 100, 0)
}