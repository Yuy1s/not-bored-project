package com.example.not_bored

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.not_bored.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding:ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        binding=ActivityMainBinding.inflate(layoutInflater)
        super.onCreate(savedInstanceState)
        setContentView(binding.root)


        binding.start.setOnClickListener(){
            val numberParticipants:Int= binding.numberParticipants.text.toString().toInt()
            startRecommendations(numberParticipants)
        }
    }

    private fun startRecommendations(numberParticipants:Int){
        if(numberParticipants>0){

          val recommendations = Intent(this,recommendationsActivity::class.java)
              recommendations.putExtra("participants",numberParticipants)
              startActivity(recommendations)

        }else{
            Toast.makeText(this, "You must enter participants", Toast.LENGTH_LONG).show()
        }
    }
}