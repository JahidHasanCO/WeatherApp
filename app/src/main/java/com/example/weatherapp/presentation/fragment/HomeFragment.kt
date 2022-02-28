package com.example.weatherapp.presentation.fragment

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.viewModels
import androidx.core.view.isVisible
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.weatherapp.R
import com.example.weatherapp.Utils.ApiState
import com.example.weatherapp.databinding.FragmentHomeBinding
import com.example.weatherapp.databinding.FragmentSplashScreenBinding
import com.example.weatherapp.presentation.adapter.CityAdapter
import com.example.weatherapp.presentation.viewmodel.CityViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collect

@AndroidEntryPoint
class HomeFragment : Fragment() {

    private val cityViewModel: CityViewModel by viewModels()
    private lateinit var binding: FragmentHomeBinding
    private lateinit var cityAdapter: CityAdapter


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentHomeBinding.inflate(inflater, container, false)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initRecyclerview()
        cityViewModel.getCity()

        lifecycleScope.launchWhenStarted {
            cityViewModel._cityStateFlow.collect {

                when (it) {
                    is ApiState.Loading -> {
                        binding.recyclerview.isVisible=false
                        binding.progressBar.isVisible=true
                    }
                    is ApiState.Failure -> {
                        binding.recyclerview.isVisible = false
                        binding.progressBar.isVisible = false
                        Log.d("main", "onCreate: ${it.msg}")
                    }
                    is ApiState.Success -> {
                        binding.recyclerview.isVisible = true
                        binding.progressBar.isVisible = false
                        cityAdapter.setData(it.data.list)
                        cityAdapter.notifyDataSetChanged()
                        Log.d("main", "onCreate: ${it.data}")
                    }
                    is ApiState.Empty -> {
                        Log.d("main", "onCreate: Empty")
                    }
                }

            }

        }

    }

    private fun initRecyclerview() {
        cityAdapter= CityAdapter(ArrayList())
        binding.recyclerview.apply {
            setHasFixedSize(true)
            layoutManager= LinearLayoutManager(context)
            adapter=cityAdapter
        }
    }

}