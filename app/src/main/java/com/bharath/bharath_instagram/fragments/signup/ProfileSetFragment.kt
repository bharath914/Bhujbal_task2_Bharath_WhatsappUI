package com.bharath.bharath_instagram.fragments.signup

import android.os.Bundle
import android.view.KeyEvent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.EditorInfo
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.bharath.bharath_instagram.R

class ProfileSetFragment : Fragment() {


    lateinit var btnNext: Button
    lateinit var editText: EditText


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        // Inflate the layout for this fragment
        val v = inflater.inflate(R.layout.fragment_profile_set, container, false)

        btnNext = v.findViewById(R.id.Ps_Btn_Next)
        editText = v.findViewById(R.id.profileSet_editText)
        editText.setOnEditorActionListener(object : TextView.OnEditorActionListener {
            override fun onEditorAction(v: TextView?, actionId: Int, event: KeyEvent?): Boolean {
                if (actionId == EditorInfo.IME_ACTION_DONE || event?.action == KeyEvent.ACTION_DOWN) {
                    if (editText.text.length in 6..12) {
                        findNavController().navigate(R.id.action_profileSetFragment_to_initializingFrag)
                    } else {
                        showAlertDialog()
                    }
                    return true
                }
                return false
            }

        })
        btnNext.setOnClickListener {

            if (editText.text.length in 6..12) {


                findNavController().navigate(R.id.action_profileSetFragment_to_initializingFrag)
            } else {
                showAlertDialog()
            }
        }


        return v
    }

    private fun showAlertDialog() {
        val builder: AlertDialog.Builder = AlertDialog.Builder(this.requireContext())
        builder.setTitle("Invalid Name")
        builder.setMessage("Name should contain atleast 6 characters and atmost 12 characters")
        builder.setPositiveButton(
            "Ok"
        ) { dialog, which -> dialog?.dismiss() }
        builder.create()
        builder.show()
    }


}