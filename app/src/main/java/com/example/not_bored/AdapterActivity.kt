package com.example.not_bored

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView

class AdapterActivity(private val activities:List<String>):RecyclerView.Adapter<ActivityViewHolder>() {

    private lateinit var listener: OnItemClickListener

    interface OnItemClickListener {
        fun onItemClick(position: Int)
    }

    fun setOnItemClickListener(listener: OnItemClickListener) {
        this.listener = listener
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ActivityViewHolder {
        val layoutInflater=LayoutInflater.from(parent.context)
        return ActivityViewHolder(layoutInflater.inflate(R.layout.item_activity,parent,false),listener)
    }

    override fun onBindViewHolder(holder: ActivityViewHolder, position: Int) {
        val activityPosition=activities[position]
        holder.bind(activityPosition)
    }

    override fun getItemCount()=activities.size

}