package com.bharath.bharath_instagram.fragments.main

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.View.GONE
import android.view.View.VISIBLE
import android.view.ViewGroup
import android.widget.ProgressBar
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.viewModelScope
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.bharath.bharath_instagram.R
import com.bharath.bharath_instagram.presentation.adatper.ProductListAdatper
import com.bharath.bharath_instagram.presentation.viewmodel.MainViewModel
import kotlinx.coroutines.launch

class ChatsFragment(private val mainViewModel: MainViewModel) : Fragment() {

    lateinit var recyclerView: RecyclerView
    lateinit var progress: ProgressBar
    lateinit var totalOrders: TextView
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        // Inflate the layout for this fragment
        val v = inflater.inflate(R.layout.fragment_chats, container, false)


        progress = v.findViewById(R.id.ChatsProgressIndicator)
        recyclerView = v.findViewById(R.id.ChatsRecycler)

        totalOrders = v.findViewById(R.id.TotalOrders)

        val adap = ProductListAdatper(requireContext())
        recyclerView.adapter = adap
        recyclerView.layoutManager = LinearLayoutManager(context)
        recyclerView.setItemViewCacheSize(8)
        recyclerView.setHasFixedSize(true)

        mainViewModel.viewModelScope.launch {
            mainViewModel.productList.collect {
                if (it.isLoading) {

                    progress.visibility = VISIBLE

                } else {

                    progress.visibility = GONE
                }
            }
        }

        mainViewModel.viewModelScope.launch {
//

            mainViewModel.allOrders.collect {

                adap.submitList(it)
                totalOrders.text = String.format(getString(R.string.total_place_holder), it.size)
            }
//
        }

        return v

    }


}