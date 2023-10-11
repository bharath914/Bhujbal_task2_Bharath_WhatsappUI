package com.bharath.bharath_instagram.fragments.main

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.viewModelScope
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.Orientation
import com.bharath.bharath_instagram.R
import com.bharath.bharath_instagram.presentation.adatper.ProductListAdatper
import com.bharath.bharath_instagram.presentation.viewmodel.MainViewModel
import kotlinx.coroutines.launch

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [chatsFragment.newInstance] factory method to
 * create an instance of this fragment.
 */

class chatsFragment(val mainViewModel: MainViewModel) : Fragment() {

    lateinit var recyclerView: RecyclerView
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        // Inflate the layout for this fragment
        val v = inflater.inflate(R.layout.fragment_chats, container, false)


        recyclerView = v.findViewById(R.id.ChatsRecycler)


        val adap = ProductListAdatper(requireContext())
        recyclerView.adapter = adap
        recyclerView.layoutManager = LinearLayoutManager(context)


        mainViewModel.viewModelScope.launch {
//
            mainViewModel.allOrders.collect {

                adap.submitList(it)
            }
//
        }

        return v

    }


}