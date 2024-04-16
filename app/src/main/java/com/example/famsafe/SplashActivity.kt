package com.example.famsafe

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity


class SplashActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)

        val isUserLoggedIn=SharedPreference.getBoolean(PrefConstant.Is_User_Logged_In)
        if(isUserLoggedIn){

            startActivity(Intent(this,MainActivity::class.java))
            finish()
        }
        else{
            startActivity(Intent(this,LoginActivity::class.java))
            finish()
        }


    }
}