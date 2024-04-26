package com.example.famsafe

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.gms.tasks.Task
import com.google.android.play.integrity.internal.i
import com.google.android.play.integrity.internal.q
import com.google.firebase.Firebase
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.auth
import com.google.firebase.firestore.DocumentChange
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.QuerySnapshot
import com.google.firebase.firestore.Source
import com.google.firebase.firestore.firestore
import kotlin.math.log

class GuardFragment : Fragment() {

    lateinit var mContext:Context
    override fun onAttach(context: Context) {
        super.onAttach(context)
        mContext=context
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_guard, container, false)


    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val send_emergency=requireView().findViewById<CardView>(R.id.sos_card)
        send_emergency.setOnClickListener {

             val user_name1:String?=SharedPreference.getString(PrefConstant.Emergency_email_id)

             if(user_name1!=null){
                 share(user_name1)
             }

        }

        val send_location_coordinates=requireView().findViewById<CardView>(R.id.green_card)
        send_location_coordinates.setOnClickListener {
            val lati:String?=SharedPreference.getString(PrefConstant.lati)
            val longi:String?=SharedPreference.getString(PrefConstant.longi)
            val user_name1:String?=SharedPreference.getString(PrefConstant.Emergency_email_id)
            share1(lati,longi,user_name1)
        }
    }
    fun share(user_name1:String){
        //implicit intent
        var i= Intent(Intent.ACTION_SEND)
        i.setType("text/plain")
        i.putExtra(Intent.EXTRA_EMAIL, arrayOf(user_name1))
        i.putExtra(Intent.EXTRA_SUBJECT,"in EMERGENCY! NEED urgent Help💀")




        startActivity(i)
    }
    fun share1(lati:String?,longi:String?,user_name1: String?){
        //implicit intent
        var i= Intent(Intent.ACTION_SEND)
        i.setType("text/plain")
        i.putExtra(Intent.EXTRA_EMAIL, arrayOf(user_name1))
        i.putExtra(Intent.EXTRA_SUBJECT, "My Location Co-ordinates are:${lati} , ${longi}")




        startActivity(i)
    }


//    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
//        super.onViewCreated(view, savedInstanceState)
//        val sendinvite=requireView().findViewById<Button>(R.id.btm_request)
//        sendinvite.setOnClickListener {
//            sendInvite()
//        }
//        Log.d("fire20", "sendinvite k baad")
//        getinvites()
//
//    }

//    private fun sendInvite() {
//
//        val mail = requireView().findViewById<EditText>(R.id.et_email).text.toString()
//        val firestore = Firebase.firestore
//        val data = hashMapOf(
//            "invite_status" to 0
//        )
//        val senderMail = FirebaseAuth.getInstance().currentUser?.email.toString()
//        firestore.collection("new")
//            .document(mail)
//            .collection("invites")
//            .document(senderMail).set(data)
//            .addOnSuccessListener {
//
//            }.addOnFailureListener {
//
//            }
//
//    }

    //collection/document/collection/document/document_fields
    //new/aroracycle.dhampur@gmail.com/invites/${aryan/invite_status,aryanarora0033@gmail.com/invite_status}

//    private fun getinvites(){
//        val firestore = Firebase.firestore
//        firestore.collection("new").
//        document("aroracycle.dhampur@gmail.com").
//        collection("invites").get().addOnCompleteListener{
//            if(it.isSuccessful){
//                val list: ArrayList<String> = ArrayList()
//                for(item in it.result){
////                    if(it.result.get("invite_status") == 0L){
////                        list.add(item.id)
//                    list.add(item.toString())
//                    }
//                val adapter = inviteMailAdapter(list)
//                val recyclerView =
//                    requireView().findViewById<RecyclerView>(R.id.email_recyclerview)
//                recyclerView.layoutManager = LinearLayoutManager(requireContext())
//                recyclerView.adapter = adapter
//                }
//
//
//            }
//
//        }

    //collection/document/collection/document/document_fields
    //new/aroracycle.dhampur@gmail.com/invites/${aryan/invite_status,aryanarora0033@gmail.com/invite_status}
//    private fun getinvites() {
//
//        val db = FirebaseFirestore.getInstance()
//
//        // Reference to your outer collection
//        val list : ArrayList<String> =ArrayList()
//        db.collection("new").get().addOnSuccessListener { documents ->
//            for (document in documents) {
//                // For each document in the outer collection, access the inner collection
//                val docId = document.id
//                db.collection("new/$docId/invites")
//                    .get()
//                    .addOnSuccessListener { innerDocuments ->
//                        for (innerDoc in innerDocuments) {
//
//                            list.add("12")
//                            // Access and print fields of each document in the inner collection
//                            //println("Document ID: ${innerDoc.id} - Fields: ${innerDoc.data}")
//                        }
//                    }
//                    .addOnFailureListener { e ->
//                        println("Error getting inner documents: $e")
//                    }
//            }
//        }
//            .addOnFailureListener { exception ->
//                println("Error getting documents: $exception")
//
//
//        val adapter = inviteMailAdapter(list)
//                    val recyclerView=requireView().findViewById<RecyclerView>(R.id.email_recyclerview)
//                    recyclerView.layoutManager=LinearLayoutManager(requireContext())
//                    recyclerView.adapter=adapter
//
//
//                }
//            }











    companion object {

        fun newInstance() = GuardFragment()

    }
}