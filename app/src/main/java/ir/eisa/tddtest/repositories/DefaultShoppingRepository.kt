package ir.eisa.tddtest.repositories

import androidx.lifecycle.LiveData
import ir.eisa.tddtest.data.local.ShoppingDao
import ir.eisa.tddtest.data.local.ShoppingItem
import ir.eisa.tddtest.data.remote.PixabayApi
import ir.eisa.tddtest.data.remote.responses.ImageResponse
import ir.eisa.tddtest.other.Resource
import retrofit2.Response
import javax.inject.Inject

class DefaultShoppingRepository @Inject constructor(
    private val shoppingDao: ShoppingDao,
    private val pixabayApi: PixabayApi
) :ShoppingRepository{

    override suspend fun insertShoppingItem(shoppingItem: ShoppingItem) {
        shoppingDao.insertShoppingItem(shoppingItem)
    }

    override suspend fun deleteShoppingItem(shoppingItem: ShoppingItem) {
        shoppingDao.deleteShoppingItem(shoppingItem)
    }

    override fun observeAllShoppingItem(): LiveData<List<ShoppingItem>> {
       return shoppingDao.observeAllShoppingItem()
    }

    override fun observeTotalPrice(): LiveData<Float> {
       return shoppingDao.observeTotalPrice()
    }

    override suspend fun searchForImage(imageQuery: String): Resource<ImageResponse> {
      return try {
          val response = pixabayApi.searchForImage(imageQuery)
          if (response.isSuccessful){
              response.body()?.let {
                  return@let Resource.success(it)
              } ?:Resource.error("An unknown error occured" , null)
          }else{
              Resource.error("An unknown error occured",null)
          }
      }catch (e:Exception){
          Resource.error("Could not the server ,. Check your internet conection",null)
      }
    }

}