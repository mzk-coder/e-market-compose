package com.mzk.samplee_market.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.mzk.samplee_market.data.models.Product

@Database(entities = [Product::class], version = 1, exportSchema = false)
abstract class EMarketDatabase : RoomDatabase() {
    abstract fun orderDao(): OrderDao
}