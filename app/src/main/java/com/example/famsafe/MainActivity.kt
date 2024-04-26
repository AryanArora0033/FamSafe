package com.example.famsafe

import android.Manifest
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.core.app.ActivityCompat
import androidx.fragment.app.Fragment
import com.example.famsafe.databinding.ActivityMainBinding
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.firebase.Firebase
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.auth
import com.google.firebase.firestore.firestore

class MainActivity : AppCompatActivity() {
    val permissionarray= arrayOf(
        Manifest.permission.ACCESS_FINE_LOCATION,
        Manifest.permission.INTERNET,
        Manifest.permission.READ_CONTACTS,

    )
    val code=786
    lateinit var binding:ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding=ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)


        askforpermission()



        binding.bottomNavbar.setOnItemSelectedListener {
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
        binding.bottomNavbar.selectedItemId=R.id.home


        //firebase initialise
        val currentuser=FirebaseAuth.getInstance().currentUser
        val name=currentuser?.displayName.toString()
        val mail=currentuser?.email.toString()
        val phone=currentuser?.phoneNumber.toString()

        //for database
        val db = Firebase.firestore
        // Create a new user with a first and last name
        val user = hashMapOf(
            "name" to "name",
            "mail" to "mail",
            "PhoneNumber" to "phone"

        )
        val detail:ArrayList<String> = ArrayList()
// Add a new document with a generated ID
        db.collection("users")
            .add(user)
            .addOnSuccessListener { documentReference ->
                Log.d("firestore89", "DocumentSnapshot added with ID: {documentReference.id}")
            }
            .addOnFailureListener { e ->
                Log.w("firestore89", "Error adding document", e)
            }
        db.collection("users").document("XitYAuaBhs9bIuhUZa5z")
            .get()
            .addOnSuccessListener {
                if(it.exists()){
                    val ans:String?=it.getString("name")
                    Log.d("details20", "${ans} ")
                }


            }


            .addOnFailureListener {

            }
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