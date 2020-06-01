package com.hellohasan.weatherappmvvmdagger.features.weather_info_show.model

import android.content.Context
import com.google.gson.GsonBuilder
import com.google.gson.reflect.TypeToken

import com.hellohasan.weatherappmvvmdagger.common.RequestCompleteListener
import com.hellohasan.weatherappmvvmdagger.features.weather_info_show.model.data_class.City
import com.hellohasan.weatherappmvvmdagger.features.weather_info_show.model.data_class.WeatherInfoResponse
import com.hellohasan.weatherappmvvmdagger.network.ApiInterface

import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.io.IOException
import javax.inject.Inject

class WeatherInfoShowModelImpl @Inject constructor(
    private val context: Context,
    private val apiInterface: ApiInterface
) : WeatherInfoShowModel {

    override fun getCityList(callback: RequestCompleteListener<MutableList<City>>) {
        try {
            val stream = context.assets.open("city_list.json")

            val size = stream.available()
            val buffer = ByteArray(size)
            stream.read(buffer)
            stream.close()
            val tContents = String(buffer)

            val groupListType = object : TypeToken<ArrayList<City>>() {}.type
            val gson = GsonBuilder().create()
            val cityList: MutableList<City> = gson.fromJson(tContents, groupListType)

            callback.onRequestSuccess(cityList) //let presenter know the city list

        } catch (e: IOException) {
            e.printStackTrace()
            callback.onRequestFailed(requireNotNull(e.localizedMessage)) //let presenter know about failure
        }
    }

    override fun getWeatherInfo(
        cityId: Int,
        callback: RequestCompleteListener<WeatherInfoResponse>
    ) {

        val call: Call<WeatherInfoResponse> = apiInterface.callApiForWeatherInfo(cityId)

        call.enqueue(object : Callback<WeatherInfoResponse> {

            // if retrofit network call success, this method will be triggered
            override fun onResponse(
                call: Call<WeatherInfoResponse>,
                response: Response<WeatherInfoResponse>
            ) {
                if (response.body() != null)
                    callback.onRequestSuccess(requireNotNull(response.body())) //let presenter know the weather information data
                else
                    callback.onRequestFailed(response.message()) //let presenter know about failure
            }

            // this method will be triggered if network call failed
            override fun onFailure(call: Call<WeatherInfoResponse>, t: Throwable) {
                callback.onRequestFailed(requireNotNull(t.localizedMessage)) //let presenter know about failure
            }
        })
    }
}