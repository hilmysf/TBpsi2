package com.example.tbpsi

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.firebase.database.*
import kotlinx.android.synthetic.main.activity_lihat_data.*

class LihatDataActivity : AppCompatActivity() {
    private lateinit var rvUser: RecyclerView
    private lateinit var adapter: ItemAdapter
    private var items: ArrayList<Barang> = arrayListOf()

    private lateinit var firebaseRef: DatabaseReference
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_lihat_data)
        supportActionBar?.setDisplayShowHomeEnabled(true)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.title = "Data Saya"
        firebaseRef = FirebaseDatabase.getInstance().getReference("barang")
        rvUser = findViewById(R.id.rv_user)
        rvUser.setHasFixedSize(true)

        firebaseRef.addValueEventListener(object : ValueEventListener {
            override fun onCancelled(p0: DatabaseError) {
                TODO("Not yet implemented")
            }

            override fun onDataChange(p0: DataSnapshot) {
                if (p0.exists()) {
                    items.clear()
                    for (h: DataSnapshot in p0.children) {
                        val item: Barang? = h.getValue(Barang::class.java)
                        if (item != null) {
                            items.add(item)
                        }
                    }
                    adapter = ItemAdapter(items, this@LihatDataActivity)
                    var layoutManager: RecyclerView.LayoutManager =
                        LinearLayoutManager(this@LihatDataActivity)
                    rvUser.layoutManager = layoutManager
                    rvUser.adapter = adapter
                }
            }
        })
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }
}