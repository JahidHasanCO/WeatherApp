package com.example.weatherapp.presentation.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.weatherapp.data.ListCity.Result
import com.example.weatherapp.databinding.CityRowBinding


class CityAdapter(private var cityList: List<Result>)
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
    }

    override fun getItemCount(): Int = cityList.size

    class CityViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){

    }

    fun setData(postList: List<Result>)
    {
        this.cityList=postList
        notifyDataSetChanged()
    }

}