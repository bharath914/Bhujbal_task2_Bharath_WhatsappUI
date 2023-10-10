package com.bharath.bharath_instagram.fragments.signup

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.addCallback
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.bharath.bharath_instagram.R
import com.bharath.bharath_instagram.data.LanguageInfo
import com.bharath.bharath_instagram.presentation.LanguageAdapter
import com.bharath.bharath_instagram.presentation.RecyclerViewClickListener
import com.google.android.material.floatingactionbutton.FloatingActionButton

class LanguageFragment : Fragment(), RecyclerViewClickListener {

    private lateinit var okBtn: FloatingActionButton

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        // Inflate the layout for this fragment
        val v = inflater.inflate(R.layout.fragment_language, container, false)
        val recyclerview = v.findViewById<RecyclerView>(R.id.language_Recycler)
        okBtn = v.findViewById(R.id.floatingActionButton)
        val navController = findNavController()
        val arrayList = arrayListOf<LanguageInfo>(
            LanguageInfo("English", "English"),
            LanguageInfo("हिन्दी", "Hindi"),
            LanguageInfo("தமிழ்", "Tamil"),
            LanguageInfo("తెలుగు", "Telugu"),
            LanguageInfo("ಕನ್ನಡ", "Kannada"),
            LanguageInfo("मराठी", "Marathi"),
            LanguageInfo("ગુજરાતી", "Gujarati"),
            LanguageInfo("اردو", "Urdu"),
            LanguageInfo("ਪੰਜਾਬੀ", "Punjabi")
        )
        requireActivity().onBackPressedDispatcher.addCallback(viewLifecycleOwner) {

            requireActivity().finishAffinity()
        }

        val languageAdapter = LanguageAdapter(v.context ,arrayList,this)


        recyclerview.adapter = languageAdapter

        recyclerview.layoutManager = LinearLayoutManager(v.context)
        recyclerview.setHasFixedSize(true)
        recyclerview.setItemViewCacheSize(6)


        okBtn.setOnClickListener {
            navigate()
        }

        return v
    }

    override fun onItemClick(position: Int) {
        navigate()
    }

    private fun navigate() {
        findNavController().navigate(R.id.action_languageFragment_to_agreeFragment)
    }


}