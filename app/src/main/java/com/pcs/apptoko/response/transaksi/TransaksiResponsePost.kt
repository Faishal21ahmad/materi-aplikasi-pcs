package com.pcs.apptoko.response.transaksi

import com.pcs.apptoko.response.produk.Produk

class TransaksiResponsePost(
    val `data`: TransaksiData,
    val message: String,
    val success: Boolean
)


data class TransaksiData (
    val `transaksi`: Transaksi
)