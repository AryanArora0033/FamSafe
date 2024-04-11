package com.example.famsafe

import android.annotation.SuppressLint
import android.content.Context
import android.os.Bundle
import android.provider.ContactsContract
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext


class HomeFragment : Fragment() {
    lateinit var inviteAdapter:ItemAdapter
    private val listcontacts:ArrayList<ContactModel> = ArrayList()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_home, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val listmembers= listOf<MemberModel>(
            MemberModel("Aryan Arora","dpr","100","220m"),
            MemberModel("Manali Arora","bijnor","98","221m"),
            MemberModel("Satish Arora","dpr","99","100m"),
            MemberModel("Satish Arora","dpr","99","100m"),
            MemberModel("Satish Arora","dpr","99","100m")


        )
        val adapter=MemberAdapter(listmembers)
        val recyclerView=requireView().findViewById<RecyclerView>(R.id.recycler_view)
        recyclerView.layoutManager=LinearLayoutManager(requireContext())
        recyclerView.adapter=adapter


        inviteAdapter=ItemAdapter(listcontacts)
        fetchAllContacts()
        // ye parallel chalega chahe kitna heavy kaam karalo kaam karalo
        CoroutineScope(Dispatchers.IO).launch {

//            listcontacts.addAll(fetchcontacts())
            insertDatabaseContacts(fetchcontacts())
            withContext(Dispatchers.Main){
                inviteAdapter.notifyDataSetChanged()
            }


        }




        val inviteRecycler=requireView().findViewById<RecyclerView>(R.id.recycler_invite)
        inviteRecycler.layoutManager=LinearLayoutManager(requireContext(),LinearLayoutManager.HORIZONTAL,false)
        inviteRecycler.adapter=inviteAdapter



    }

    private fun fetchAllContacts() {
        val database=MyFamilyDatabase.getDatabase(requireContext())
        database.contactDao().getAllContacts().observe(viewLifecycleOwner){
            listcontacts.clear();
            listcontacts.addAll(it)
            inviteAdapter.notifyDataSetChanged()
        }

    }

    private suspend fun insertDatabaseContacts(listcontacts: ArrayList<ContactModel>) {
        val database=MyFamilyDatabase.getDatabase(requireContext())
        database.contactDao().insertAll(listcontacts)
    }

    @SuppressLint("Range", "SuspiciousIndentation")
    private fun fetchcontacts(): ArrayList<ContactModel> {
        val listcontacts:ArrayList<ContactModel> =ArrayList()
        val cr=requireActivity().contentResolver
        val cursor=cr.query(ContactsContract.Contacts.CONTENT_URI,null,null,null,null)
        if(cursor!=null && cursor.count>0) {
            while (cursor != null && cursor.moveToNext()) {

                val id = cursor.getString(cursor.getColumnIndex(ContactsContract.Contacts._ID))
                val name =
                    cursor.getString(cursor.getColumnIndex(ContactsContract.Contacts.DISPLAY_NAME))
                val hasphonenumber =
                    cursor.getInt(cursor.getColumnIndex(ContactsContract.Contacts.HAS_PHONE_NUMBER))

                if (hasphonenumber > 0) {
                    val pcur = cr.query(
                        ContactsContract.CommonDataKinds.Phone.CONTENT_URI, null,
                        ContactsContract.CommonDataKinds.Phone.CONTACT_ID + "= ?", arrayOf(id), ""
                    )
                    if (pcur != null && pcur.count > 0) {
                        while (pcur != null && pcur.moveToNext()) {
                            val phonenum =
                                pcur.getString(pcur.getColumnIndex(ContactsContract.CommonDataKinds.Phone.NUMBER))
                                listcontacts.add(ContactModel(name, phonenum))
                        }
                        pcur.close()

                    }
                }

            }
            if (cursor != null) {
                cursor.close()
            }

        }
        return listcontacts


    }

    companion object {

        fun newInstance() = HomeFragment()

    }
}