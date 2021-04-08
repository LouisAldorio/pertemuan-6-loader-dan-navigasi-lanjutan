package com.mindorks.framework.loaderdannavigasilanjutan

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.activity_detail.*

class DetailActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)

        val myListContact = listOf(
            myContact(nama = "Sunaryo", nomorHp = "08774844***4"),
            myContact(nama = "Agus", nomorHp = "08774844***4")
        )
        val contactAdapter = myAdapterRecycleView(myListContact)
        myRecyView.apply {
            layoutManager = LinearLayoutManager(this@DetailActivity)
            adapter = contactAdapter
        }
    }
}