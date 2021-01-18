package com.androidstrike.javit.interfaces

import com.androidstrike.javit.models.OverView
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.create
import retrofit2.http.GET

interface OverViewAPI {

    @GET("ed4008c8")
    fun getOverview(): Call<List<OverView>>

    companion object{
        operator fun invoke(): OverViewAPI{
            return Retrofit.Builder()
                .baseUrl("https://api.mocki.io/v1/")
                .addConverterFactory(GsonConverterFactory.create())
                .build()
                .create(OverViewAPI::class.java)
        }
    }
}