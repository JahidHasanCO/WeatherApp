package com.example.weatherapp.di

import com.example.weatherapp.Utils.Constants
import com.example.weatherapp.network.ApiService
import com.example.weatherapp.repo.CityRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideRetrofitService(): ApiService {
        return Retrofit.Builder().baseUrl(Constants.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build().create(ApiService::class.java)
    }

//    @Provides
//    fun provideCityRepository(
//        apiService: ApiService
//    ): CityRepository {
//        return CityRepository(apiService)
//    }

}