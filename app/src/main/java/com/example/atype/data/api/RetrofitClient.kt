package com.example.atype.data.api

import com.google.gson.GsonBuilder
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitClient {
    private lateinit var  retrofitClient: Retrofit
  val apiService: KakaoAPI
      get() = instance.create(KakaoAPI::class.java)

    private val instance : Retrofit
        private get() {
            val gson = GsonBuilder().setLenient().create()
            return Retrofit.Builder()
                .baseUrl(Constants.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build()
        }
}


