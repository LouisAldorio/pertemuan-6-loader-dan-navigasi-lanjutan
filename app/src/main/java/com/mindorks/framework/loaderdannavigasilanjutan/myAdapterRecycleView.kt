package com.mindorks.framework.loaderdannavigasilanjutan

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.layout_recy_view.view.*

class myAdapterRecycleView(private val contact: List<myContact>) : RecyclerView.Adapter<myHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): myHolder {
        return myHolder(
            LayoutInflater.from(parent.context)
                .inflate(R.layout.layout_recy_view,parent,false)
        )
    }

    override fun onBindViewHolder(holder: myHolder, position: Int) {
        holder.bindContact(contact[position])
    }

    override fun getItemCount(): Int = contact.size

}

class myHolder(view: View) : RecyclerView.ViewHolder(view) {
    private val contactName = view.itemName
    private val contactNumber = view.itemNumber

    fun bindContact(tmp: myContact) {
        contactName.text = "${contactName.text} : ${tmp.nama}"
        contactNumber.text = "${contactNumber.text} : ${tmp.nomorHp}"
    }
}