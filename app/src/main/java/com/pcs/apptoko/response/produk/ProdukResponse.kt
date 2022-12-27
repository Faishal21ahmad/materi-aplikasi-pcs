package com.pcs.apptoko.response.produk

import com.pcs.apptoko.response.itemTransaksi.Data

data class ProdukResponse(
    val `data`: Data,
    val message:String,
    val success: Boolean
)

