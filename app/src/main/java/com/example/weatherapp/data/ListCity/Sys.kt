package com.example.weatherapp.data.ListCity


import com.google.gson.annotations.SerializedName

data class Sys(
    @SerializedName("country")
    val country: String
)