package com.example.tbpsi

import android.app.AlertDialog
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import kotlinx.android.synthetic.main.item_cardview_item.view.*

class ItemAdapter(private val listItem: ArrayList<Barang>, val mCtx: Context) :
    RecyclerView.Adapter<ItemAdapter.CardViewViewHolder>() {
    internal var items = arrayListOf<Barang>()

    inner class CardViewViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bind(item: Barang) {
            with(itemView) {
                tv_name.text = item.namaBarang
                tv_brand.text = item.brand
                tv_stok.text = item.jmlStok.toString()

                ib_edit.setOnClickListener {
                    showUpdateDialog(item)
                }
            }
        }
    }

    fun showUpdateDialog(barang: Barang) {
        val builder = AlertDialog.Builder(mCtx)
        builder.setTitle("Edit Data")
        val inflater = LayoutInflater.from(mCtx)
        val view = inflater.inflate(R.layout.update_dialog, null)

        val etNamaBarang = view.findViewById<EditText>(R.id.et_namaBarang)
        val etBrand = view.findViewById<EditText>(R.id.et_brand)
        val etJmlStok = view.findViewById<EditText>(R.id.et_jmlStok)
        val etKategori = view.findViewById<EditText>(R.id.et_kategori)


        etNamaBarang.setText(barang.namaBarang)
        etBrand.setText(barang.brand)
        etJmlStok.setText(barang.jmlStok.toString())
        etKategori.setText(barang.kategori)

        builder.setView(view)
        builder.setPositiveButton("Update") { p0, p1 ->
            val dbBrg: DatabaseReference = FirebaseDatabase.getInstance().getReference("barang")
            val namaBarang = etNamaBarang.text.toString().trim()
            val namaBrand = etBrand.text.toString().trim()
            val jmlStok = etJmlStok.text.toString().trim()
            val kategori = etKategori.text.toString().trim()
            if (namaBarang.isEmpty()) {
                etNamaBarang.error = "Mohon nama diisi"
                etNamaBarang.requestFocus()
                return@setPositiveButton
            }
            if (namaBrand.isEmpty()) {
                etBrand.error = "Mohon nama diisi"
                etBrand.requestFocus()
                return@setPositiveButton
            }
            if (jmlStok.isEmpty()) {
                etJmlStok.error = "Mohon nama diisi"
                etJmlStok.requestFocus()
                return@setPositiveButton
            }
            if (kategori.isEmpty()) {
                etKategori.error = "Mohon nama diisi"
                etKategori.requestFocus()
                return@setPositiveButton
            }

            val barang = Barang(barang.id, namaBarang, namaBrand, jmlStok.toInt(), kategori)
            dbBrg.child(barang.id!!).setValue(barang)

            Toast.makeText(mCtx, "Data berhasil di update", Toast.LENGTH_SHORT).show()
        }
        builder.setNegativeButton("No") { p0, p1 ->

        }
        val alert: AlertDialog = builder.create()
        alert.show()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CardViewViewHolder {
        var view: View =
            LayoutInflater.from(parent.context).inflate(R.layout.item_cardview_item, parent, false)
        return CardViewViewHolder(view)
    }

    override fun getItemCount(): Int {
        return listItem.size
    }

    override fun onBindViewHolder(holder: CardViewViewHolder, position: Int) {
        holder.bind(listItem[position])
    }
}