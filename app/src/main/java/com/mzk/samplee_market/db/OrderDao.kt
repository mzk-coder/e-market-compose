package com.mzk.samplee_market.db

import androidx.room.*
import com.mzk.samplee_market.data.models.OrderValue
import com.mzk.samplee_market.data.models.Product
import com.mzk.samplee_market.utils.Constants.ORDER_TABLE
import kotlinx.coroutines.flow.Flow


@Dao
interface OrderDao {

    @Query("SELECT * FROM $ORDER_TABLE ORDER BY id ASC")
    fun getAllProduct() : Flow<List<Product>>


    @Query("SELECT SUM(quantity) as count, SUM(quantity * price) as total FROM $ORDER_TABLE")
    fun getOrderValue() : Flow<OrderValue>

    @Query("SELECT * FROM $ORDER_TABLE WHERE id = :productId")
    fun getSelectedProduct(productId : Int) : Flow<Product?>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addProduct(product: Product)

    @Delete
    suspend fun deleteProduct(product: Product)

    @Update
    suspend fun updateProduct(product: Product)

    @Query("DELETE FROM $ORDER_TABLE")
    suspend fun deleteAll()

}