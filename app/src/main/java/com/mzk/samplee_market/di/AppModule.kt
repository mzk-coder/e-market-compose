package com.mzk.samplee_market.di

import android.content.Context
import androidx.room.Room
import com.google.gson.GsonBuilder
import com.mzk.samplee_market.data.repository.DefaultEMarketRepository
import com.mzk.samplee_market.data.repository.EMarketRepository
import com.mzk.samplee_market.db.EMarketDatabase
import com.mzk.samplee_market.db.OrderDao
import com.mzk.samplee_market.network.ApiService
import com.mzk.samplee_market.utils.Constants.BASE_URL
import com.mzk.samplee_market.utils.Constants.DATABASE_NAME
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Singleton
    @Provides
    fun provideApiService(): ApiService {

//        val builder = OkHttpClient.Builder()
//        val httpLoggingInterceptor = HttpLoggingInterceptor()
//        httpLoggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY)
//        builder.networkInterceptors().add(httpLoggingInterceptor)
//        builder.connectTimeout(5, TimeUnit.MINUTES)
//        builder.readTimeout(2, TimeUnit.MINUTES)
//        builder.writeTimeout(2, TimeUnit.MINUTES)


        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create(GsonBuilder().create()))
            .build()
            .create(ApiService::class.java)
    }


    @Singleton
    @Provides
    fun provideDatabase(@ApplicationContext context: Context) =
        Room.databaseBuilder(context, EMarketDatabase::class.java, DATABASE_NAME).build()


    @Singleton
    @Provides
    fun provideOrderDao(eMarketDatabase : EMarketDatabase) = eMarketDatabase.orderDao()


    @Singleton
    @Provides
    fun provideDefaultEMarketRepository(apiService: ApiService, orderDao: OrderDao)
    = DefaultEMarketRepository(orderDao = orderDao, apiService = apiService) as EMarketRepository



}