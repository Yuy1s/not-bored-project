package com.example.not_bored

import android.content.Intent
import android.os.Bundle
import android.os.PersistableBundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.not_bored.databinding.ActivitiesBinding



class recommendationsActivity:AppCompatActivity(){
    private lateinit var adapter:AdapterActivity
    private lateinit var binding:ActivitiesBinding

    val itemsActivities = listOf(
        "Education",
        "Recreational",
        "Social",
        "DIY",
        "Charity",
        "Cooking",
        "Relaxation",
        "Music",
        "Busywork"
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        binding=ActivitiesBinding.inflate(layoutInflater)
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        val participants= intent.getIntExtra("participants",0)

        setupRecyclerView(participants)
        binding.rvItemActivities.layoutManager = LinearLayoutManager(this)
        binding.randomBtn.setOnClickListener(){

        }
    }

    private fun setupRecyclerView(participants:Int=0) {
        adapter = AdapterActivity(itemsActivities)
        binding.rvItemActivities.adapter = adapter
        adapter.setOnItemClickListener(object : AdapterActivity.OnItemClickListener {
            override fun onItemClick(position: Int) {
                val intent = Intent(this@recommendationsActivity, SuggestionActivity::class.java)
//                Log.i("ITEM PRESSED", itemsActivities[position])
                intent.putExtra("participants", participants)
                intent.putExtra("type", itemsActivities[position])
                startActivity(intent)
            }
        })
    }
}