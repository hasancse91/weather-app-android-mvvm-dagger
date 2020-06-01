package com.hellohasan.weatherappmvvmdagger.di

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.hellohasan.weatherappmvvmdagger.features.weather_info_show.model.WeatherInfoShowModel
import com.hellohasan.weatherappmvvmdagger.features.weather_info_show.model.WeatherInfoShowModelImpl
import com.hellohasan.weatherappmvvmdagger.features.weather_info_show.viewmodel.WeatherInfoViewModel
import com.hellohasan.weatherappmvvmdagger.features.weather_info_show.viewmodel.WeatherInfoViewModelFactory

import dagger.Binds
import dagger.Module

@Module
abstract class MainActivityModule {

    @Binds
    abstract fun bindMainViewModel(viewModel: WeatherInfoViewModel): ViewModel

    @Binds
    abstract fun bindModel(weatherInfoShowModelImpl: WeatherInfoShowModelImpl): WeatherInfoShowModel

    @Binds
    abstract fun bindWeatherInfoViewModelFactory(factory: WeatherInfoViewModelFactory): ViewModelProvider.Factory
}