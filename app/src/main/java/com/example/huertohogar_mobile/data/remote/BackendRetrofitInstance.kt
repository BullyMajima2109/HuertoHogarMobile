package com.example.huertohogar_mobile.data.remote

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object BackendRetrofitInstance {

    // IMPORTANTE: Emulador Android usa 10.0.2.2 para acceder a tu localhost del PC
    private const val BASE_URL = "http://10.0.2.2:8080/"

    val api: BackendApiService by lazy {
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(BackendApiService::class.java)
    }
}
