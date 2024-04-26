package com.example.famsafe

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class inviteMailAdapter(private val listinvites:List<String>):
    RecyclerView.Adapter<inviteMailAdapter.ViewHolder>() {

    class ViewHolder(private val item:View):RecyclerView.ViewHolder(item){
        val name=item.findViewById<TextView>(R.id.tv_id)

    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): inviteMailAdapter.ViewHolder {
        val inflater=LayoutInflater.from(parent.context)
        val item=inflater.inflate(R.layout.item_invite_mail,parent,false)
        return ViewHolder(item)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        
        val item=listinvites[position]
        holder.name.text=item
    }

    override fun getItemCount(): Int {
        return listinvites.size
    }




}