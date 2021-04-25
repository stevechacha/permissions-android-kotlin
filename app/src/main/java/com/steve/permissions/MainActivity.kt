package com.steve.permissions

import android.Manifest
import android.content.pm.PackageManager
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import androidx.core.app.ActivityCompat

class MainActivity : AppCompatActivity() {
    lateinit var RequestBtn:Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        RequestBtn=findViewById(R.id.btnPermisson)
        RequestBtn.setOnClickListener {
            requestPermission()
        }

    }


    //check the permission

    private fun hasWriteExternalStoragePermission()=
            ActivityCompat.checkSelfPermission(this,Manifest.permission.WRITE_EXTERNAL_STORAGE)== PackageManager.PERMISSION_GRANTED

    private fun hasLocationForegroundPermission()=
            ActivityCompat.checkSelfPermission(this,Manifest.permission.ACCESS_COARSE_LOCATION)== PackageManager.PERMISSION_GRANTED
    private fun hasLocationBackgroundPermission()=
            ActivityCompat.checkSelfPermission(this,Manifest.permission.ACCESS_BACKGROUND_LOCATION)== PackageManager.PERMISSION_GRANTED

    private fun requestPermission(){
        //sent through array of string
        //creating mutable list
        var permissToRequest= mutableListOf<String>()
        if (!hasWriteExternalStoragePermission()){
            permissToRequest.add(Manifest.permission.WRITE_EXTERNAL_STORAGE)
        }
        if (!hasLocationForegroundPermission()){
            permissToRequest.add(Manifest.permission.ACCESS_COARSE_LOCATION)
        }
        if (!hasLocationBackgroundPermission()){
            permissToRequest.add(Manifest.permission.ACCESS_BACKGROUND_LOCATION)
        }
        if (permissToRequest.isEmpty()){
            ActivityCompat.requestPermissions(this,permissToRequest.toTypedArray(),0)
        }

    }
    //otherside fun for perission

    override fun onRequestPermissionsResult(
            requestCode: Int,
            permissions: Array<out String>,
            grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        if (requestCode==0 && grantResults.isEmpty()){
            //loping

            for (i in grantResults.indices)
                if (grantResults[i]==PackageManager.PERMISSION_GRANTED){
                    Log.d("PermissionsRequest","${permissions[i]} granted")
                }

        }
    }
}
