package com.bharath.bharath_instagram.fragments.signup

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.bharath.bharath_instagram.R

class AgreeFragment : Fragment() {


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_agree, container, false)

        val btn = view.findViewById<Button>(R.id.Btn_AgreeAndContinue)

        val navController = findNavController()
        btn.setOnClickListener {
            navController.navigate(R.id.action_agreeFragment_to_phoneNumber)
        }

        return view
    }

}