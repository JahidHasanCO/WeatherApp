package com.example.weatherapp.presentation.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.weatherapp.Utils.ApiState
import com.example.weatherapp.Utils.Resource
import com.example.weatherapp.data.ListCity.CityList
import com.example.weatherapp.repo.CityRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CityViewModel
@Inject
constructor(private val cityRepository: CityRepository) : ViewModel() {

    private val cityStateFlow: MutableStateFlow<ApiState>
            = MutableStateFlow(ApiState.Empty)

    val _cityStateFlow: StateFlow<ApiState> = cityStateFlow

    fun getCity() = viewModelScope.launch {
        cityStateFlow.value = ApiState.Loading
        cityRepository.getCitys()
            .catch { e->
                cityStateFlow.value=ApiState.Failure(e)
            }.collect {
                cityStateFlow.value = ApiState.Success(it)
            }
    }
}

