package com.bharath.bharath_instagram.fragments.signup

import android.content.Context
import android.os.Bundle
import android.view.KeyEvent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.EditorInfo
import android.view.inputmethod.InputMethodManager
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.bharath.bharath_instagram.R

class PhoneNumber : Fragment() {
    lateinit var nextButton: Button
    lateinit var phoneNumber: EditText

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        // Inflate the layout for this fragment
        val v = inflater.inflate(R.layout.fragment_phone_number, container, false)
        nextButton = v.findViewById(R.id.Bt_Next_PhoneNum)
        phoneNumber = v.findViewById(R.id.Et_phoneNumber)
        phoneNumber.requestFocus()

        phoneNumber.setOnEditorActionListener(object : TextView.OnEditorActionListener {
            override fun onEditorAction(v: TextView?, actionId: Int, event: KeyEvent?): Boolean {
                if (actionId == EditorInfo.IME_ACTION_DONE || event?.action == KeyEvent.ACTION_DOWN) {
                    if (phoneNumber.text.length == 10 && containsOnlyNumbers(phoneNumber.text.toString())) {
                        navigate()
                    } else {
                        showAlertDialog()
                    }
                    return true
                }
                return false
            }

        })

        showSoftKeyboard(phoneNumber)
        nextButton.setOnClickListener {
            if (phoneNumber.text.length == 10 && containsOnlyNumbers(phoneNumber.text.toString())) {
                navigate()
            } else {
                showAlertDialog()
            }

        }

        return v
    }

    private fun navigate() {
        findNavController().navigate(R.id.action_phoneNumber_to_verifyPhoneNum)

    }

    private fun showAlertDialog() {
        val builder: AlertDialog.Builder = AlertDialog.Builder(this.requireContext())
        builder.setTitle("Invalid Phone Number")
        builder.setMessage("Please Enter 10 Digit Mobile Number")
        builder.setPositiveButton(
            "Ok"
        ) { dialog, which -> dialog?.dismiss() }
        builder.create()
        builder.show()
    }

    private fun showSoftKeyboard(view: View) {
        if (view.requestFocus()) {
            val imm =
                requireActivity().getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
            imm.showSoftInput(view, InputMethodManager.SHOW_IMPLICIT)
        }
    }

    fun containsOnlyNumbers(input: String): Boolean {
        val regex = Regex("^[0-9]+$")
        return regex.matches(input)
    }
}