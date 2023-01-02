package com.aplikasi.pcs.adapter

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.pcs.apptoko.LoginActivity
import com.pcs.apptoko.R
import com.pcs.apptoko.api.BaseRetrofit
import com.pcs.apptoko.response.produk.Produk
import com.pcs.apptoko.response.produk.ProdukResponsePost

import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class ProdukAdapter(val listProduk: List<Produk>):RecyclerView.Adapter<ProdukAdapter.ViewHolder>()  {
    private  val api by lazy { BaseRetrofit().endpoint }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProdukAdapter.ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_produk,parent,false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolde, position: Int) {
        val produk = listProduk[position]

        holder.txtNamaProduk.text = produk.nama
        holder.txtHarga.text = produk.harga

        val token = LoginActivity.sessionManager.getString("TOKEN")

        holder.btnDelete.setOnClickListener {
            Toast.makeText(holder.itemView.context, produk.nama.toString(), Toast.LENGTH_LONG)
                .show()

            api.deleteProduk(token.toString(), produk.id.toInt()).enqueue(object :
                Callback<ProdukResponsePost> {
                override fun onResponse(
                    call: Call<ProdukResponsePost>,
                    response: Response<ProdukResponsePost>
                ) {
                    Log.d("Data", response.toString())
                    Toast.makeText(holder.itemView.context, "Data d i hapus", Toast.LENGTH_LONG)
                        .show()
                    holder.itemView.findNavControlter().navigate(R.id.produkFragment)
                }

                override fun onFailure(call: Call<ProdukResponsePost>, t: Throwable) {
                    Log.e("Data", t.toString())
                }
            })
        }

        holder.btnEdit.set0nClickListener {
            val bundle = Bundle()
            bundle.putParcelable("produk",produk)
            bundle.putString("status","edit")
            holder.itemView.findNavController().navigate(R.id.produkFormFragment,bundle)
        }

        override fun getItemCount(): Int {
            return listProduk.size
        }

        class ViewHolder(itemView : View) : RecyclerView.ViewHolder(itemView){
            val txtNamaProduk = itemView.findViewById(R.id.txtNamaProduk) as TextView
            val txtHarga = itemView.findViewById(R.id.txtHarga) as TextView
            val btnDelete = itemView.findViewById(R.id.btnDelete) as ImageButton
            val btnEdit = itemView.findViewById(R.id.btnEdit) as ImageButton

        }
    }



}