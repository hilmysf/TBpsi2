package com.example.tbpsi

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_home.*

class homeActivity : AppCompatActivity(), View.OnClickListener {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)
        supportActionBar?.title = "mWareHouse"
        LL_btn_data_saya.setOnClickListener(this)
        LL_btn_tambah_data.setOnClickListener(this)

    }

    override fun onClick(v: View?) {

        if (v?.id == R.id.LL_btn_data_saya) {
            intent = Intent(applicationContext, LihatDataActivity::class.java)
            startActivity(intent)
        } else if (v?.id == R.id.LL_btn_tambah_data) {
            intent = Intent(applicationContext, tambahActivity::class.java)
            startActivity(intent)
        }
    }
}