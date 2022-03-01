package com.example.weatherapp.presentation.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.example.weatherapp.Utils.Utils
import com.example.weatherapp.data.ListCity.list
import com.example.weatherapp.databinding.CityRowBinding
import com.example.weatherapp.presentation.fragment.HomeFragmentDirections
import com.example.weatherapp.presentation.fragment.SplashScreenFragmentDirections
import java.math.RoundingMode
import java.text.DecimalFormat


class CityAdapter(private var cityList: List<list>)
    : RecyclerView.Adapter<CityAdapter.CityViewHolder>() {
    private lateinit var binding:CityRowBinding

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CityViewHolder {
        binding = CityRowBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,false)
        return CityViewHolder(binding.root)
    }

    override fun onBindViewHolder(holder: CityViewHolder, position: Int) {
        binding.cityName.text=cityList[position].name
        binding.status.text = cityList[position].weather[0].main

        val cel : Double = cityList[position].main.temp - 273.15
        val df = DecimalFormat("#.##")
        df.roundingMode = RoundingMode.DOWN
        val roundoff = df.format(cel)

        binding.temp.text = "$roundoff \u2103"

        binding.fullRow.setOnClickListener {
            Utils.clearCity()
            Utils.addCity(cityList[position])
            binding.root.findNavController().navigate(HomeFragmentDirections.actionHomeFragmentToDetailsFragment())
        }

    }

    override fun getItemCount(): Int = cityList.size

    class CityViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){

    }

    fun setData(postList: List<list>)
    {
        this.cityList=postList
        notifyDataSetChanged()
    }

}