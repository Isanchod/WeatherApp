package com.weatherApp.core.ui

import android.os.Bundle
import com.weatherApp.R
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.Fragment
import org.koin.androidx.viewmodel.ext.android.viewModel

class LocationFragment : Fragment(R.layout.fragment_location){

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        val viewModel: WeatherViewModel by viewModel()
        val cityEdit = view.findViewById<EditText>(R.id.editCity)
        val latEdit = view.findViewById<EditText>(R.id.editLatitude)
        val lonEdit = view.findViewById<EditText>(R.id.editLongitude)
        val saveBtn = view.findViewById<Button>(R.id.btnSave)

        if(viewModel.getLocation().first == null) {
            updateCurrentDataTextView(view,getString(R.string.no_data))
        }else{
            updateCurrentDataTextView(view,getString(R.string.saved_data,
                viewModel.getLocation().first,viewModel.getLocation().second,viewModel.getLocation().third))
        }

        saveBtn.setOnClickListener {
            val city = cityEdit.text.toString()
            val lat = latEdit.text.toString()
            val lon = lonEdit.text.toString()
            if (city.isEmpty() || lat.isEmpty() || lon.isEmpty()){
                Toast.makeText(requireContext(), getString(R.string.error_empty_field), Toast.LENGTH_SHORT).show()
            }
            else{
                viewModel.saveLocation(city, lat.toDouble(), lon.toDouble())
                Toast.makeText(requireContext(), getString(R.string.successfully_saved), Toast.LENGTH_SHORT).show()
                updateCurrentDataTextView(view,getString(R.string.saved_data,
                    viewModel.getLocation().first,viewModel.getLocation().second,viewModel.getLocation().third))
                cityEdit.text.clear()
                latEdit.text.clear()
                lonEdit.text.clear()
            }
        }
    }

    fun updateCurrentDataTextView(view: View, text:String){
        val currentData = view.findViewById<TextView>(R.id.textCurrent)
        currentData.text = text
    }
}
