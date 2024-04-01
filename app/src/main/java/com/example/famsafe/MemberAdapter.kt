package com.example.famsafe

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class MemberAdapter(private val listmembers: List<MemberModel>) : RecyclerView.Adapter<MemberAdapter.ViewHolder>() {
    class ViewHolder(val item:View):RecyclerView.ViewHolder(item) {
                val imageuser=item.findViewById<ImageView>(R.id.img_person)
                val name=item.findViewById<TextView>(R.id.txt_person)

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MemberAdapter.ViewHolder {
           val inflater=LayoutInflater.from(parent.context)
           val item =inflater.inflate(R.layout.item_member,parent,false)
           return ViewHolder(item)

    }

    override fun onBindViewHolder(holder: MemberAdapter.ViewHolder, position: Int) {
        val item=listmembers[position]
        holder.name.text=item.name
    }

    override fun getItemCount(): Int {
        return listmembers.size
    }
}