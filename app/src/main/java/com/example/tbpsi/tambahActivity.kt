package com.example.tbpsi

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.google.firebase.database.FirebaseDatabase

class tambahActivity : AppCompatActivity(), View.OnClickListener {
    private lateinit var etNamaBarang : EditText
    private lateinit var etBrand : EditText
    private lateinit var etJmlStok : EditText
    private lateinit var etKategori : EditText
    private lateinit var btnSave : Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_tambah)
        supportActionBar?.setDisplayShowHomeEnabled(true)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.title = "Add Data"

        etNamaBarang = findViewById(R.id.et_namaBarang)
        etBrand = findViewById(R.id.et_brand)
        etJmlStok = findViewById(R.id.et_jmlStok)
        etKategori = findViewById(R.id.et_kategori)
        btnSave = findViewById(R.id.btn_simpan)

        btnSave.setOnClickListener(this)
    }

    override fun onClick(v: View?) {
        simpanData()
    }

    private fun simpanData() {
        val namaBarang = etNamaBarang.text.toString()
        val brand = etBrand.text.toString()
        val jmlStok = etJmlStok.text.toString()
        val kategori = etKategori.text.toString()

        if (namaBarang.isEmpty()) {
            etNamaBarang.error = "Nama barang kosong!"
            return
        }
        if (brand.isEmpty()) {
            etBrand.error = "Brand kosong!"
            return
        }
        if (jmlStok.isEmpty()) {
            etJmlStok.error = "Jumlah stok kosong!"
            return
        }
        if (kategori.isEmpty()) {
            etKategori.error = "Kategori kosong!"
            return
        }
        val firebaseRef = FirebaseDatabase.getInstance().getReference("barang")
        val barangId = firebaseRef.push().key
        val brg = Barang(barangId!!, namaBarang, brand, jmlStok.toInt(), kategori)
        if (barangId != null) {
            firebaseRef.child(barangId).setValue(brg).addOnCompleteListener{
                Toast.makeText(applicationContext, "Data berhasil ditambahkan", Toast.LENGTH_SHORT).show()
            }
        }
    }
    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }
}