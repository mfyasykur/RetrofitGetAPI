package com.ppb.retrofitgetapi.adapter

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.widget.AppCompatImageButton
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView
import com.ppb.retrofitgetapi.fitur.EditActivity
import com.ppb.retrofitgetapi.Mahasiswa
import com.ppb.retrofitgetapi.network.ApiClient
import com.ppb.retrofitgetapi.R
import com.ppb.retrofitgetapi.ResponseDataDeleteMahasiswa
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class RVAdapter(
    private val context: Context,
    private val dataList: ArrayList<Mahasiswa>
): RecyclerView.Adapter<RVAdapter.MyViewHolder>() {

    class MyViewHolder(val view: View): RecyclerView.ViewHolder(view) {

        val tvNim: TextView = view.findViewById(R.id.tv_nim)
        val tvNama: TextView = view.findViewById(R.id.tv_nama)
        val tvTelp: TextView = view.findViewById(R.id.tv_telp)
        val cvMain: CardView = view.findViewById(R.id.cv_main)
        val ibDelete: AppCompatImageButton = view.findViewById(R.id.ib_delete)
        val ibEdit: AppCompatImageButton = view.findViewById(R.id.ib_edit)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {

        val layoutInflater = LayoutInflater.from(parent.context)
        val itemView = layoutInflater.inflate(R.layout.item_layout, parent, false)

        return MyViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {

        holder.tvNim.text = dataList[position].nim
        holder.tvNama.text = dataList[position].nama
        holder.tvTelp.text = dataList[position].telepon

        holder.cvMain.setOnClickListener {
            Toast.makeText(context, dataList[position].nama, Toast.LENGTH_SHORT).show()
        }

        holder.ibDelete.setOnClickListener {
            val nim = dataList[position].nim
            this.remoteDeleteMahasiswa(nim)
        }

        holder.ibEdit.setOnClickListener {
            val intent = Intent(context, EditActivity::class.java)
            val bundle = Bundle()
            bundle.putString("nim", dataList[position].nim)
            bundle.putString("nama", dataList[position].nama)
            bundle.putString("telepon", dataList[position].telepon)
            intent.putExtras(bundle)
            context.startActivity(intent)
        }
    }

    override fun getItemCount(): Int {

        return dataList.size
    }

    fun setData(data: List<Mahasiswa>) {

        dataList.clear()
        dataList.addAll(data)
        notifyDataSetChanged()
    }

    private fun remoteDeleteMahasiswa(nim: String) {

        ApiClient.apiService.deleteMahasiswa(nim).enqueue(object : Callback<ResponseDataDeleteMahasiswa> {
            override fun onResponse(
                call: Call<ResponseDataDeleteMahasiswa>,
                response: Response<ResponseDataDeleteMahasiswa>
            ) {
                if (response.isSuccessful) {
                    val apiResponse = response.body()
                    val data = apiResponse?.data

                    if (data != null) {
                        // find index of deleted item in dataList
                        val index = dataList.indexOfFirst {
                            it.nim == nim
                        }

                        if (index != -1 && index < dataList.size) {
                            // remove item from dataList
                            dataList.removeAt(index)

                            //notify the adapter that item has been removed
                            notifyItemRemoved(index)
                            Toast.makeText(context, "Data terhapus", Toast.LENGTH_SHORT).show()
                        }
                    }
                }
            }

            override fun onFailure(call: Call<ResponseDataDeleteMahasiswa>, t: Throwable) {
                Log.d("Error", t.stackTraceToString())
            }
        })
    }

}