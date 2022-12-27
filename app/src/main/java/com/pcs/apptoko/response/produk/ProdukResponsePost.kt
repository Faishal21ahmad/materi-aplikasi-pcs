package com.pcs.apptoko.response.produk

import com.pcs.apptoko.response.itemTransaksi.Data

data class ProdukResponsePost(
    val `data`: DataProduk,
    val message: String,
    val success: Boolean
)

data class DataProduk (
    val `produk`: Produk
    )