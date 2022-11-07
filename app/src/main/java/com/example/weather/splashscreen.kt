package com.example.weather

import android.annotation.SuppressLint
import android.content.Intent
import android.location.Location
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.util.Log
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationServices

class splashscreen : AppCompatActivity() {
    lateinit var mfusedlocation: FusedLocationProviderClient
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splashscreen)
        mfusedlocation = LocationServices.getFusedLocationProviderClient(this)



        getLastLocation()


    }

    @SuppressLint("MissingPermission")
    private fun getLastLocation() {
        if (checkLocation()) {
            if (LocationEnable()) {
                mfusedlocation.lastLocation.addOnCompleteListener { task ->
                    var location: Location? = task.result
                    if (location == null) {
                        NewLocation()
                    } else {
                        Log.i("Location", location.longitude.toString())
                    }
                }
            }else{
                Toast.makeText(this,"please on your GPS",Toast.LENGTH_LONG).show()
            }


        } else {
            requestpermission()
        }
    }

    private fun NewLocation() {

    }

    private fun LocationEnable(): Boolean {

    }

    private fun requestpermission() {

    }

    private fun checkLocation(): Boolean {

    }
}