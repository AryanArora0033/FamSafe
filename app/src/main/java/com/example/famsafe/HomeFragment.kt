package com.example.famsafe

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView


class HomeFragment : Fragment() {

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


    }

    companion object {

        fun newInstance() = HomeFragment()

    }
}