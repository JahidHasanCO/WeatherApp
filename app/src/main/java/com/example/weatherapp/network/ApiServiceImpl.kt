package com.example.weatherapp.network

import com.example.weatherapp.data.ListCity.CityList
import com.example.weatherapp.data.ListCity.Weather
import com.example.weatherapp.data.ListCity.list
import javax.inject.Inject


class ApiServiceImpl @Inject constructor(private val apiService: ApiService) {

    suspend fun getCitys(): CityList = apiService.getAllCitys()

    suspend fun getTemp(lat :String, lon:String): Weather = apiService.getTemp(lat,lon)
}