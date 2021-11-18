package com.example.not_bored

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.example.not_bored.databinding.SuggestionActivityBinding
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class SuggestionActivity : AppCompatActivity(){
    private lateinit var binding: SuggestionActivityBinding

    override fun onCreate(savedInstanceState: Bundle?) {
       binding= SuggestionActivityBinding.inflate(layoutInflater)
       super.onCreate(savedInstanceState)
       setContentView(binding.root)
       val participants= intent.getIntExtra("participants",0)
       val type= intent.getStringExtra("type")?:""
       searchActivity(type,participants)

        binding.btnTryAnother.setOnClickListener {
            Log.i("SUGGESTION ACTIVITY", "Try Another button was pressed")
            searchActivity(type, participants)
        }
   }

   private  fun searchActivity(type:String="", participants:Int){
       CoroutineScope(Dispatchers.IO).launch {

           val call= getActivitiesRetroFit().create(APIService::class.java)
               .getActivities("?participants=$participants")

           val response:ActivitiesInfo?=call.body()

           runOnUiThread {
               binding.typeActivity.text=type
               if (call.isSuccessful && response?.activity != null) {
                   val price: Int = response.price.times(10).toInt()
                   binding.tvTittle.text = response.activity
                   binding.tvParticipants.text = response.participants.toString()
                   binding.tvCategory.text = response.type

                   when (price) {
                       0 -> binding.tvPrice.text = "Free"
                       in 1..3 -> binding.tvPrice.text = "Low"
                       in 4..6 -> binding.tvPrice.text = "Medium"
                       in 7..10 -> binding.tvPrice.text = "High"
                   }
               } else {
                   binding.tvTittle.text = "Sorry, I didn't find any activity"
               }

           }
       }
   }
   private fun getActivitiesRetroFit(): Retrofit {
       return Retrofit.Builder().baseUrl("https://www.boredapi.com/api/activity/")
           .addConverterFactory(GsonConverterFactory.create())
           .build()
   }
}