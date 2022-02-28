package com.example.weatherapp.network

import com.example.weatherapp.data.ListCity.CityList
import com.example.weatherapp.data.ListCity.Result
import javax.inject.Inject


class ApiServiceImpl @Inject constructor(private val apiService: ApiService) {

    suspend fun getCitys(): List<Result> = apiService.getAllCitys()
}