package com.hellohasan.weatherappmvvmdagger.features.weather_info_show.model

import com.hellohasan.weatherappmvvmdagger.common.RequestCompleteListener
import com.hellohasan.weatherappmvvmdagger.features.weather_info_show.model.data_class.City
import com.hellohasan.weatherappmvvmdagger.features.weather_info_show.model.data_class.WeatherInfoResponse

interface WeatherInfoShowModel {
    fun getCityList(callback: RequestCompleteListener<MutableList<City>>)
    fun getWeatherInfo(cityId: Int, callback: RequestCompleteListener<WeatherInfoResponse>)
}