package com.example.myapplication

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import android.widget.Button
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import com.example.myapplication.databinding.ActivityMainBinding
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class MainActivity : AppCompatActivity() {
    lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.button.setOnClickListener {
            fetchVacancies()
        }
    }


    object RetrofitInstance {
        val api: ApiService by lazy {
            Retrofit.Builder()
                .baseUrl("https://opendata.trudvsem.ru/api/v1/")
                .addConverterFactory(GsonConverterFactory.create())
                .build()
                .create(ApiService::class.java)
        }
    }


    private fun fetchVacancies() {
        val call = RetrofitInstance.api.getVacancies()

        call.enqueue(object : Callback<VacanciesResponse> {
            override fun onResponse(call: Call<VacanciesResponse>, response: Response<VacanciesResponse>) {
                if (response.isSuccessful) {
                    response.body()?.let { vacanciesResponse ->
                        val vacancyNames = vacanciesResponse.results.vacancies.joinToString("\n") { vacancy ->
                            vacancy.details.company.name
                        }

                        binding.textView.text = vacancyNames
                    }
                } else {
                    Log.e("Error", "Response not successful: ${response.code()}")
                }

            }

            override fun onFailure(call: Call<VacanciesResponse>, t: Throwable) {
                Log.e("Error", "Failed to fetch vacancies: ${t.message}")
            }
        })
    }
}

