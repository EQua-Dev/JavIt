package com.androidstrike.javit.interfaces

import com.androidstrike.javit.models.OverView
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.create
import retrofit2.http.GET

//Interface to build and create the API Call
interface OverViewAPI {

    @GET("e4af6937")
//    api.mocki.io/v2/e4af6937
//    @GET("/overviews")
//    @GET("posts")
    fun getOverview(): Call<List<OverView>>

    companion object{
        operator fun invoke(): OverViewAPI{
            return Retrofit.Builder()
//                .baseUrl("https://jsonplaceholder.typicode.com/")
//                .baseUrl("https://javit.free.beeceptor.com")
                .baseUrl("https://api.mocki.io/v2/")
                .addConverterFactory(GsonConverterFactory.create())
                .build()
                .create(OverViewAPI::class.java)
        }
    }
}