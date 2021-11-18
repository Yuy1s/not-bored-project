package com.example.not_bored

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.example.not_bored.databinding.ItemActivityBinding


class ActivityViewHolder(view: View,listener: AdapterActivity.OnItemClickListener):RecyclerView.ViewHolder(view) {
    private val binding= ItemActivityBinding.bind(view)
    fun bind(activityPosition:String){
        binding.nameActivity.text=activityPosition
    }
    init {
        view.setOnClickListener {
            listener.onItemClick(adapterPosition)
        }
    }

}
