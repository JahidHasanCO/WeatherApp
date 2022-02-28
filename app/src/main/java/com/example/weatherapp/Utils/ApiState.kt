package com.example.weatherapp.Utils

import com.example.weatherapp.data.ListCity.CityList
import com.example.weatherapp.data.ListCity.list

sealed class ApiState{
    object Loading : ApiState()
    class Failure(val msg:Throwable) : ApiState()
    class Success(val data: CityList) : ApiState()
    object Empty : ApiState()
}