package com.ppb.retrofitgetapi

import retrofit2.Call
import retrofit2.http.GET

interface ApiService {

    @GET("datamahasiswa/")
    fun getDataMahasiswa(): Call<ApiResponse>
}