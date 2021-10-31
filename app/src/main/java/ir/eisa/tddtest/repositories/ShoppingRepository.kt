package ir.eisa.tddtest.repositories

import androidx.lifecycle.LiveData
import ir.eisa.tddtest.data.local.ShoppingItem
import ir.eisa.tddtest.data.remote.responses.ImageResponse
import ir.eisa.tddtest.other.Resource


interface ShoppingRepository {

    suspend fun insertShoppingItem(shoppingItem: ShoppingItem)

    suspend fun deleteShoppingItem(shoppingItem: ShoppingItem)

    fun observeAllShoppingItem():LiveData<List<ShoppingItem>>

    fun observeTotalPrice():LiveData<Float>

    suspend fun searchForImage(imageQuery:String): Resource<ImageResponse>
}