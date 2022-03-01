package com.example.weatherapp.Utils

import com.example.weatherapp.data.ListCity.CityList
import com.example.weatherapp.data.ListCity.Weather
import com.example.weatherapp.data.ListCity.list

sealed class ApiStateTemp{
    object Loading : ApiStateTemp()
    class Failure(val msg:Throwable) : ApiStateTemp()
    class Success(val data: Weather) : ApiStateTemp()
    object Empty : ApiStateTemp()
}