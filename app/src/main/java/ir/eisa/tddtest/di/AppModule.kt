package ir.eisa.tddtest.di

import android.content.Context
import androidx.room.Room
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.internal.managers.ApplicationComponentManager
import dagger.hilt.android.qualifiers.ApplicationContext
import ir.eisa.tddtest.data.local.ShoppingItemDataBase
import ir.eisa.tddtest.data.remote.PixabayApi
import ir.eisa.tddtest.other.Constants.BASE_URL
import ir.eisa.tddtest.other.Constants.DATABASE_NAME
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(ApplicationComponentManager::class)
class AppModule {

    @Singleton
    @Provides
    fun provideShoppingItemDataBase(
        @ApplicationContext context: Context
    )=Room.databaseBuilder(context,ShoppingItemDataBase::class.java,DATABASE_NAME).build()

    @Singleton
    @Provides
    fun provideShoppingItemDao(
       dataBase: ShoppingItemDataBase
    )= dataBase.shoppingDao()


    @Singleton
    @Provides
    fun providePixabayApi():PixabayApi{
        return Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create())
            .baseUrl(BASE_URL)
            .build()
            .create(PixabayApi::class.java)
    }
}