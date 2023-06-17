package com.ppb.retrofitgetapi.network

import com.ppb.retrofitgetapi.ApiResponse
import com.ppb.retrofitgetapi.ResponseDataDeleteMahasiswa
import com.ppb.retrofitgetapi.ResponseDataInsertMahasiswa
import com.ppb.retrofitgetapi.ResponseDataUpdateMahasiswa
import retrofit2.Call
import retrofit2.http.DELETE
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path

interface ApiService {

    // Retrieve
    @GET("datamahasiswa/")
    fun getDataMahasiswa(): Call<ApiResponse>

    // Insert
    @FormUrlEncoded
    @POST("datamahasiswa/")
    fun insertMahasiswa(
        @Field("nim") nim: String,
        @Field("nama") nama: String,
        @Field("telepon") telepon: String
    ): Call<ResponseDataInsertMahasiswa>

    // Delete
    @DELETE("datamahasiswa/{nim}")
    fun deleteMahasiswa(@Path("nim") nim: String): Call<ResponseDataDeleteMahasiswa>

    // Update
    @FormUrlEncoded
    @POST("datamahasiswa/{nim}")
    fun updateMahasiswa(
        @Path("nim") nim: String,
        @Field("nama") nama: String,
        @Field("telepon") telepon: String
    ): Call<ResponseDataUpdateMahasiswa>
}