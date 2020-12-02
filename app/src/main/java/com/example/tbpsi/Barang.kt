package com.example.tbpsi

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Barang(
    val id : String,
    val namaBarang : String,
    val brand : String,
    val jmlStok : Int,
    val kategori : String
): Parcelable {
    constructor(): this("","","",0,"")
}