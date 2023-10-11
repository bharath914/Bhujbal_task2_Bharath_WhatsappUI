package com.bharath.bharath_instagram.fragments.main

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.View.GONE
import android.view.View.VISIBLE
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.bharath.bharath_instagram.R
import com.bharath.bharath_instagram.presentation.adatper.GroupsAdapter
import com.bharath.bharath_instagram.presentation.viewmodel.MainViewModel
import com.google.android.material.progressindicator.LinearProgressIndicator
import kotlinx.coroutines.launch


class CommunityFrag(
    private val mainViewModel: MainViewModel,
) : Fragment() {

    lateinit var recylerView: RecyclerView
    lateinit var progress: LinearProgressIndicator


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        // Inflate the layout for this fragment
        val v = inflater.inflate(R.layout.fragment_community, container, false)


        recylerView = v.findViewById(R.id.REcyclerViewCommunity)
        progress = v.findViewById(R.id.CommunityProgressIndicator)
        recylerView.layoutManager = LinearLayoutManager(requireContext())
        val adap = GroupsAdapter(requireContext())


        recylerView.adapter = adap
        mainViewModel.viewModelScope.launch {


            mainViewModel.productList.collect {
                if (it.isLoading) {
                    progress.visibility = VISIBLE
                    progress.show()
                } else {
                    progress.hide()
                    progress.visibility = GONE

                    adap.submitList(it.data.data)
                }

            }
        }


        return v
    }


}