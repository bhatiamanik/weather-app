package com.example.weather

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.location.Location
import android.location.LocationManager
import android.location.LocationRequest
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.util.Log
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.core.app.ActivityCompat
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationCallback
import com.google.android.gms.location.LocationResult
import com.google.android.gms.location.LocationServices
import java.util.jar.Manifest

class splashscreen : AppCompatActivity() {
    lateinit var mfusedlocation: FusedLocationProviderClient
    private var myRequestCode = 1010
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
                mfusedlocation.lastLocation.addOnCompleteListener {
                        task ->
                    var location: Location? = task.result
                    if (location == null) {
                        NewLocation()
                    } else {
                        Log.i("Location", location.longitude.toString())
                        Toast.makeText(this, location.longitude.toString(), Toast.LENGTH_LONG).show()
                        Toast.makeText(this, location.latitude.toString(), Toast.LENGTH_LONG).show()
                    }
                }
            } else {
                Toast.makeText(this, "please on your GPS", Toast.LENGTH_LONG).show()
            }


        } else {
            requestpermission()
        }
    }

    @SuppressLint("MissingPermission")
    private fun NewLocation() {
        var locationRequest = com.google.android.gms.location.LocationRequest()
        locationRequest.priority =
            com.google.android.gms.location.LocationRequest.PRIORITY_HIGH_ACCURACY
        locationRequest.interval = 0
        locationRequest.fastestInterval = 0
        locationRequest.numUpdates = 1
        mfusedlocation = LocationServices.getFusedLocationProviderClient(this)
        mfusedlocation.requestLocationUpdates(locationRequest,locationCallBack, Looper.myLooper())
    }
    private val locationCallBack=object :LocationCallback(){
        override fun onLocationResult(p0:LocationResult){
            var lastLocation:Location=p0.lastLocation
        }
    }

    private fun LocationEnable(): Boolean {
        var locationManager = getSystemService(Context.LOCATION_SERVICE) as LocationManager
        return locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER) || locationManager.isProviderEnabled(
            LocationManager.NETWORK_PROVIDER
        )
    }

    private fun requestpermission() {
        ActivityCompat.requestPermissions(
            this, arrayOf(
                android.Manifest.permission.ACCESS_COARSE_LOCATION,
                android.Manifest.permission.ACCESS_FINE_LOCATION
            ), myRequestCode
        )

    }

    private fun checkLocation(): Boolean {
        if (
            ActivityCompat.checkSelfPermission(
                this,
                android.Manifest.permission.ACCESS_COARSE_LOCATION
            )
            == PackageManager.PERMISSION_GRANTED ||
            ActivityCompat.checkSelfPermission(
                this,
                android.Manifest.permission.ACCESS_FINE_LOCATION
            )
            == PackageManager.PERMISSION_GRANTED
        ) {
            return true
        }
        return false

    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        if (requestCode == myRequestCode) {
            if (grantResults.isNotEmpty() && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                getLastLocation()
            }

        }
    }
}