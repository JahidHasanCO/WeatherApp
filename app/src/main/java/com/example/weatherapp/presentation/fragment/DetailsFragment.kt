package com.example.weatherapp.presentation.fragment

import android.Manifest
import android.content.pm.PackageManager
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.app.ActivityCompat
import androidx.fragment.app.Fragment
import androidx.navigation.findNavController
import com.example.weatherapp.R
import com.example.weatherapp.Utils.Utils
import com.example.weatherapp.databinding.FragmentDetailsBinding
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions
import java.math.RoundingMode
import java.text.DecimalFormat


class DetailsFragment : Fragment() {

    private lateinit var binding: FragmentDetailsBinding
    lateinit var googleMap: GoogleMap
    lateinit var mapFragment : SupportMapFragment

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        binding = FragmentDetailsBinding.inflate(inflater, container, false)



        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.backBtn.setOnClickListener {
            binding.root.findNavController().navigate(DetailsFragmentDirections.actionDetailsFragmentToHomeFragment())
        }

        setData()


        mapFragment = (childFragmentManager.findFragmentById(R.id.googleMAP) as SupportMapFragment?)!!
//        mapFragment.getMapAsync(this)
        mapFragment.getMapAsync(OnMapReadyCallback {
            googleMap = it
            if (ActivityCompat.checkSelfPermission(
                    context!!,
                    Manifest.permission.ACCESS_FINE_LOCATION
                ) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(
                    context!!,
                    Manifest.permission.ACCESS_COARSE_LOCATION
                ) != PackageManager.PERMISSION_GRANTED
            ) {
                return@OnMapReadyCallback
            }
            googleMap.isMyLocationEnabled = true

            if(Utils.cityOne != null) {

                val location1 = LatLng(Utils.cityOne!!.coord.lat, Utils.cityOne!!.coord.lon)
                googleMap.addMarker(MarkerOptions().position(location1).title(Utils.cityOne!!.name))
                googleMap.animateCamera(CameraUpdateFactory.newLatLngZoom(location1, 5f))
            }


        })

    }

    private fun setData() {
        if(Utils.cityOne != null){
            binding.name.text = Utils.cityOne!!.name
            binding.status.text = Utils.cityOne!!.weather[0].main
            binding.wind.text = "Wind Spreed: " + Utils.cityOne!!.wind.speed.toString()
            binding.humidity.text = "humidity: " +  Utils.cityOne!!.main.humidity


            val cel1 : Double = Utils.cityOne!!.main.tempMax - 273.15
            val df1 = DecimalFormat("#.##")
            df1.roundingMode = RoundingMode.DOWN
            val roundoff1 = df1.format(cel1)

            val cel2 : Double = Utils.cityOne!!.main.tempMin - 273.15
            val df2 = DecimalFormat("#.##")
            df2.roundingMode = RoundingMode.DOWN
            val roundoff2 = df2.format(cel2)

            val cel3 : Double = Utils.cityOne!!.main.temp - 273.15
            df2.roundingMode = RoundingMode.DOWN
            val roundoff3 = df2.format(cel3)

            binding.maxTemp.text = "Max. Temp: $roundoff1 \u2103"
            binding.minTemp.text = "Min. Temp: $roundoff2 \u2103"
            binding.temp.text = "$roundoff3 \u2103"


        }
    }


}