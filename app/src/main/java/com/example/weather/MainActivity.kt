package com.example.weather

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.android.volley.Request
import com.android.volley.Response
import com.android.volley.toolbox.JsonObjectRequest
import com.android.volley.toolbox.RequestFuture
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val lat =intent.getStringExtra("lat")
        val long =intent.getStringExtra("long")
        getJsonData(lat, long)
    }

    private fun getJsonData(lat:String?,long:String?)
    {
        val API_Key="d782c3641dcc9cbcb8528dfb0245b3e4"
          val queue =Volley.newRequestQueue(this)
        val url="https://api.openweathermap.org/data/2.5/weather?lat=${lat}&lon=${long}&appid=${API_Key}"
        val jsonRequest=JsonObjectRequest(
            Request.Method.GET,url,null,
            Response.Listener{response ->

            },
        Response.ErrorListener{Toast.makeText(this,"ERROR ",Toast.LENGTH_LONG).show()})

        queue.add(jsonRequest)


    }
}