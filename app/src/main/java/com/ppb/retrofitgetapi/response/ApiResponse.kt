package com.ppb.retrofitgetapi

import com.google.gson.annotations.SerializedName

data class ApiResponse(
    @SerializedName("status") val status: String,
    @SerializedName("data") val data: List<Mahasiswa>
)

data class ResponseDataInsertMahasiswa(
    @SerializedName("data")
    val data: String,
    @SerializedName("status")
    val status: String
)

data class ResponseDataUpdateMahasiswa(
    @SerializedName("data")
    val data: String,
    @SerializedName("status")
    val status: String
)

data class ResponseDataDeleteMahasiswa(
    @SerializedName("data")
    val data: String,
    @SerializedName("status")
    val status: String
)

data class Mahasiswa(
    @SerializedName("NIM") val nim: String,
    @SerializedName("Nama") val nama: String,
    @SerializedName("Telepon") val telepon: String
)
