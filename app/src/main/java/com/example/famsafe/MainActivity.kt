package com.example.famsafe

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.Fragment
import com.google.android.material.bottomnavigation.BottomNavigationView

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val bottomnav=findViewById<BottomNavigationView>(R.id.bottom_navbar)

        bottomnav.setOnItemSelectedListener {
            if(it.itemId==R.id.guard){
                inflatefragment(GuardFragment.newInstance())
            }
            else if(it.itemId==R.id.home){
                inflatefragment(HomeFragment.newInstance())
            }
            true

        }

    }

    fun inflatefragment(newinstance:Fragment) {
        val transaction=supportFragmentManager.beginTransaction()
        transaction.replace(R.id.container,newinstance )
        transaction.commit()
    }


}