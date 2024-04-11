package com.example.famsafe

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class ItemAdapter(private val listcontacts:List<ContactModel>):
           RecyclerView.Adapter<ItemAdapter.ViewHolder>()
{
    class ViewHolder(private val item: View) : RecyclerView.ViewHolder(item){
        val name=item.findViewById<TextView>(R.id.name)


    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemAdapter.ViewHolder {
        val inflater=LayoutInflater.from(parent.context)
        val item=inflater.inflate(R.layout.item_invite,parent,false)
        return ViewHolder(item)

    }

    override fun getItemCount(): Int {
        return listcontacts.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item=listcontacts[position]
        holder.name.text=item.name
    }
}