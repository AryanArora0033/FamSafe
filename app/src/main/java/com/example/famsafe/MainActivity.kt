package com.example.famsafe

import android.Manifest
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.core.app.ActivityCompat
import androidx.fragment.app.Fragment
import com.google.android.material.bottomnavigation.BottomNavigationView

class MainActivity : AppCompatActivity() {
    val permissionarray= arrayOf(
        Manifest.permission.ACCESS_FINE_LOCATION,
        Manifest.permission.INTERNET
    )
    val code=786
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        askforpermission()

        val bottomnav=findViewById<BottomNavigationView>(R.id.bottom_navbar)

        bottomnav.setOnItemSelectedListener {
            if(it.itemId==R.id.guard){
                inflatefragment(GuardFragment.newInstance())
            }
            else if(it.itemId==R.id.home){
                inflatefragment(HomeFragment.newInstance())
            }
            else if(it.itemId==R.id.dashboard){
                inflatefragment(MapsFragment())
            }
            else if(it.itemId==R.id.profile){
                inflatefragment(ProfileFragment.newInstance())
            }
            true

        }
        bottomnav.selectedItemId=R.id.home

    }

    private fun askforpermission() {
        ActivityCompat.requestPermissions(this,permissionarray,code)
    }

    fun inflatefragment(newinstance:Fragment) {
        val transaction=supportFragmentManager.beginTransaction()
        transaction.replace(R.id.container,newinstance )
        transaction.commit()
    }


}