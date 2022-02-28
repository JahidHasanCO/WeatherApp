package com.example.weatherapp.network

import com.example.weatherapp.data.ListCity.CityList
import com.example.weatherapp.data.ListCity.list
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {

    @GET("data/2.5/find?lat=23.68&lon=90.35&cnt=50&appid=e384f9ac095b2109c751d95296f8ea76")
    suspend fun getAllCitys(): CityList
}