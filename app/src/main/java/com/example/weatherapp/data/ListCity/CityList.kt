package com.example.weatherapp.data.ListCity


import com.google.gson.annotations.SerializedName

data class CityList(
    @SerializedName("cod")
    val cod: String,
    @SerializedName("count")
    val count: Int,
    @SerializedName("list")
    val list: List<list>,
    @SerializedName("message")
    val message: String
)