package com.example.weatherapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.activity.viewModels
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import com.example.weatherapp.Utils.ApiState
import com.example.weatherapp.presentation.viewmodel.CityViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collect

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    private val cityViewModel: CityViewModel by viewModels()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        cityViewModel.getCity()

        lifecycleScope.launchWhenStarted {
            cityViewModel._cityStateFlow.collect {

                when (it) {
                    is ApiState.Loading -> {
//                        binding.recyclerview.isVisible=false
//                        binding.progressBar.isVisible=true
                    }
                    is ApiState.Failure -> {
//                        binding.recyclerview.isVisible = false
//                        binding.progressBar.isVisible = false
                        Log.d("main", "onCreate: ${it.msg}")
                    }
                    is ApiState.Success -> {
//                        binding.recyclerview.isVisible = true
//                        binding.progressBar.isVisible = false
//                        postAdapter.setData(it.data)
//                        postAdapter.notifyDataSetChanged()
                        Log.d("MAIN", "onCreate: ${it.data}")
                    }
                    is ApiState.Empty -> {

                    }
                }

            }

        }
    }

}