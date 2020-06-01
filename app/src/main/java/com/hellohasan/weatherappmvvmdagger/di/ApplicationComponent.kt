package com.hellohasan.weatherappmvvmdagger.di

import android.app.Application
import com.hellohasan.weatherappmvvmdagger.common.App
import com.hellohasan.weatherappmvvmdagger.features.weather_info_show.viewmodel.WeatherInfoViewModel

import dagger.BindsInstance
import dagger.Component

import dagger.android.AndroidInjector
import dagger.android.support.AndroidSupportInjectionModule
import javax.inject.Singleton

@Singleton
@Component(
    modules = [
        AndroidSupportInjectionModule::class,
        AppModule::class]
)
interface ApplicationComponent : AndroidInjector<App> {

    fun inject(weatherInfoViewModel: WeatherInfoViewModel)

    @Component.Builder
    interface Builder {

        @BindsInstance
        fun application(application: Application): Builder

        fun build(): ApplicationComponent
    }
}