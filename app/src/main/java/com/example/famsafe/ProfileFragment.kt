package com.example.famsafe

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.TextView


class ProfileFragment : Fragment() {

    lateinit var emaill:String
    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)




    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_profile, container, false)
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val nameOfUser=SharedPreference.getString(PrefConstant.Name_of_User)
        val name=requireView().findViewById<TextView>(R.id.tv_profile)
        name.text=nameOfUser

        //adding emergency ids in shared preferences
        val click_add=requireView().findViewById<Button>(R.id.btn_emegency_id)
        click_add.setOnClickListener{
        val et_email=requireView().findViewById<EditText>(R.id.et_emergency_id)
        emaill=et_email.text.toString()
        SharedPreference.putString(PrefConstant.Emergency_email_id,emaill)



        }
        val show=requireView().findViewById<TextView>(R.id.email_to_show)
        val emaill=SharedPreference.getString(PrefConstant.Emergency_email_id)
        show.text=emaill
    }


    companion object {

        fun newInstance() = ProfileFragment()
    }
}