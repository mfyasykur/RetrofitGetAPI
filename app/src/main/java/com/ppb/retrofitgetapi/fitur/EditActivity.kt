package com.ppb.retrofitgetapi.fitur

import android.annotation.SuppressLint
import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import com.google.android.material.button.MaterialButton
import com.google.android.material.textfield.TextInputEditText
import com.ppb.retrofitgetapi.R
import com.ppb.retrofitgetapi.ResponseDataUpdateMahasiswa
import com.ppb.retrofitgetapi.network.ApiClient
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class EditActivity : AppCompatActivity() {

    private lateinit var inputNim: TextInputEditText
    private lateinit var inputNama: TextInputEditText
    private lateinit var inputTelepon: TextInputEditText
    private lateinit var btnUpdate: MaterialButton

    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_edit)

        inputNama = findViewById(R.id.input_nama)
        inputNim = findViewById(R.id.input_nim)
        inputTelepon = findViewById(R.id.input_telepon)
        btnUpdate = findViewById(R.id.btn_update)

        val bundle = intent.extras

        if (bundle != null) {
            val nim = bundle.getString("nim")
            val nama = bundle.getString("nama")
            val telepon = bundle.getString("telepon")

            inputNim.setText(nim)
            inputNama.setText(nama)
            inputTelepon.setText(telepon)
        }

        btnUpdate.setOnClickListener {
            val nim = inputNim.text.toString()
            val nama = inputNama.text.toString()
            val telepon = inputTelepon.text.toString()

            updateDataMahasiswa(nim, nama, telepon)
        }
    }

    private fun updateDataMahasiswa(nim: String, nama: String, telepon: String) {
        ApiClient.apiService.updateMahasiswa(nim, nama, telepon)
            .enqueue(object : Callback<ResponseDataUpdateMahasiswa> {
                override fun onResponse(
                    call: Call<ResponseDataUpdateMahasiswa>,
                    response: Response<ResponseDataUpdateMahasiswa>
                ) {
                    if (response.isSuccessful) {
                        val responseData = response.body()
                        if (responseData != null) {
                            if (response.isSuccessful) {
                                Toast.makeText(this@EditActivity, "Data berhasil diubah", Toast.LENGTH_SHORT).show()

                                val resultIntent = Intent()
                                resultIntent.putExtra("nim", nim)
                                resultIntent.putExtra("nama", nama)
                                resultIntent.putExtra("telepon", telepon)
                                setResult(Activity.RESULT_OK, resultIntent)
                                finish()

                            } else {
                                Toast.makeText(this@EditActivity, "Data gagal diubah!", Toast.LENGTH_SHORT).show()
                            }
                        } else {
                            Toast.makeText(this@EditActivity, "Data Kosong", Toast.LENGTH_SHORT).show()
                        }
                    } else {
                        val errorResponse = response.errorBody()?.string()
                        Toast.makeText(this@EditActivity, "API call failed: $errorResponse", Toast.LENGTH_SHORT).show()
                    }
                }

                override fun onFailure(call: Call<ResponseDataUpdateMahasiswa>, t: Throwable) {
                    Log.e("Error", t.message.toString())
                    Toast.makeText(this@EditActivity, "Error: ${t.message}", Toast.LENGTH_SHORT).show()
                }
            })
    }
}