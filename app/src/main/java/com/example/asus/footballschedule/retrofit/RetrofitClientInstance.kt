package com.example.asus.footballschedule.retrofit

import com.example.asus.footballschedule.BuildConfig
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class RetrofitClientInstance private constructor() {

    companion object {
        private val mInstance: Retrofit = Retrofit.Builder()
                .baseUrl(BuildConfig.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
        @Synchronized
        fun getInstance(): Retrofit {
            return mInstance
        }
    }

}