package com.ppb.retrofitgetapi

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView

class RVAdapter(
    private val context: Context,
    private val dataList: ArrayList<Mahasiswa>
): RecyclerView.Adapter<RVAdapter.MyViewHolder>() {

    class MyViewHolder(val view: View): RecyclerView.ViewHolder(view) {

        val tvNim: TextView = view.findViewById(R.id.tv_nim)
        val tvNama: TextView = view.findViewById(R.id.tv_nama)
        val tvTelp: TextView = view.findViewById(R.id.tv_telp)
        val cvMain: CardView = view.findViewById(R.id.cv_main)
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
    }

    override fun getItemCount(): Int {

        return dataList.size
    }

    fun setData(data: List<Mahasiswa>) {

        dataList.clear()
        dataList.addAll(data)
        notifyDataSetChanged()
    }

}