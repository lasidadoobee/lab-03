package com.example.listycity3

import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf

class CityRepository {
    private val _cities = mutableStateListOf(
        City("Edmonton", "AB"),
        City("Vancouver", "BC"),
        City("Toronto", "ON")
    )

    private val _selectedCity =  mutableStateListOf(
        City(name = "", province = "")
     )

    private val _updatedCityNames = mutableStateListOf(
        "",
        ""
    )

    val cities: List<City>
        get() = _cities

    val selectedCity: City
        get() = _selectedCity[0]

    val updateCityName: String
        get() = _updatedCityNames[0]

    val updateCityProvince: String
        get() = _updatedCityNames[1]

    fun addCity (city: City){
        _cities.add(city)
    }

    fun modUpdateCity(
        newName: String = updateCityName,
    ){
        _updatedCityNames[0] = newName
    }

    fun modUpdateProvince(
        newProvince: String = updateCityProvince
    ){
        _updatedCityNames[1] = newProvince
    }

    fun updateCity(){
        val index = _cities.indexOf(selectedCity)
        if (updateCityName == ""){
        //this would be delete city
        } else {
            if (updateCityProvince == ""){
                modUpdateProvince(selectedCity.province)
            }
                var updatedCity:City = City(name = updateCityName, province = updateCityProvince)
                _cities[index] = updatedCity
        }
    }

    fun selectCity(city : City){
        modUpdateCity(newName = city.name)
        modUpdateProvince(newProvince = city.province)
        _selectedCity[0] = city
    }

    fun clearSelectedCity(){
        _selectedCity[0] = City(name = "", province = "")
    }

}