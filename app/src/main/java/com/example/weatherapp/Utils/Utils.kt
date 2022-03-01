package com.example.weatherapp.Utils

import com.example.weatherapp.data.ListCity.list

object Utils {

     var cityOne: list? = null

    fun addCity(list: list){
        cityOne = list
    }

    fun clearCity(){
        cityOne = null
    }

}