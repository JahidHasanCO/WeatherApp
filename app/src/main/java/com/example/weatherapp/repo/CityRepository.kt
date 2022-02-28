package com.example.weatherapp.repo

import com.example.weatherapp.data.ListCity.CityList
import com.example.weatherapp.data.ListCity.Result
import com.example.weatherapp.network.ApiService
import com.example.weatherapp.network.ApiServiceImpl
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

class CityRepository
@Inject
constructor(private val apiServiceImpl: ApiServiceImpl) {

    fun getCitys(): Flow<List<Result>> = flow {
        emit(apiServiceImpl.getCitys())
    }.flowOn(Dispatchers.IO)

}