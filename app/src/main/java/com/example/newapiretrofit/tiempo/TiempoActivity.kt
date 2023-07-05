package com.example.newapiretrofit.tiempo

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.recyclerview.widget.LinearLayoutManager
//import com.example.newapiretrofit.databinding.ActivityMainBinding
import com.example.newapiretrofit.databinding.ActivityTiempoBinding
//import okhttp3.Response
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class TiempoActivity : AppCompatActivity() {

    private lateinit var binding: ActivityTiempoBinding
    var URLAPI = "https://samples.openweathermap.org/"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityTiempoBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.tiempoActivityRV.layoutManager = LinearLayoutManager(this)
        binding.tiempoActivityRV.adapter = null

        var retrofitTiempo = Retrofit.Builder()
            .baseUrl(URLAPI)
            .addConverterFactory(GsonConverterFactory.create())//permite convertir el json a clase
            .build()

        var ApiTiempo = retrofitTiempo.create(ApiTiempo::class.java)
        var callTiempo = ApiTiempo.getTiempo()

        //Empieza a bajarse los datos
        callTiempo.enqueue(object :Callback<Metereologia>{

            override fun onResponse(
                call: Call<Metereologia>,
                response: retrofit2.Response<Metereologia>
            ) {
                for (res in response.body()?.list!!){
                    Log.e("TAG Respuesta Temperatura: ", res.main.temp)
                    Log.e("TAG Respuesta Humedad", res.main.humidity)
                    Log.e("TAG Respuesta Temperatura minima: ", res.main.temp_min)
                }
            }

            override fun onFailure(call: Call<Metereologia>, t: Throwable) {
                Log.e("TAG Fallo: ", t.toString())
            }
        })
    }
}