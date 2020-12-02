package com.example.tbpsi

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_home.*

class homeActivity : AppCompatActivity(), View.OnClickListener {
    private lateinit var myData : Button
    private lateinit var tambahData : Button
    private lateinit var hapusData : Button
    private lateinit var updateData : Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)
        supportActionBar?.title = "mWareHouse"
        btn_data_saya.setOnClickListener(this)
        btn_tambah_data.setOnClickListener(this)

    }

    override fun onClick(v: View?) {

        if (v?.id == R.id.btn_data_saya){
            intent = Intent(applicationContext, LihatDataActivity::class.java)
            startActivity(intent)
        }
        else if (v?.id == R.id.btn_tambah_data){
            intent = Intent(applicationContext, tambahActivity::class.java)
            startActivity(intent)
        }
        else if (v?.id == R.id.btn_hapus_data){
            intent = Intent(applicationContext, tambahActivity::class.java)
            startActivity(intent)
        }
        else if (v?.id == R.id.btn_update_data){
            intent = Intent(applicationContext, tambahActivity::class.java)
            startActivity(intent)
        }
    }
}