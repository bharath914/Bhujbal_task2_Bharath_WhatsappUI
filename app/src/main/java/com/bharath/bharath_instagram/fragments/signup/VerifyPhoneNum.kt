package com.bharath.bharath_instagram.fragments.signup

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.InputMethodManager
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.bharath.bharath_instagram.R
import `in`.aabhasjindal.otptextview.OTPListener
import `in`.aabhasjindal.otptextview.OtpTextView


class VerifyPhoneNum : Fragment() {


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        // Inflate the layout for this fragment
        val v = inflater.inflate(R.layout.fragment_verify_phone_num, container, false)
        val otp = v.findViewById<OtpTextView>(R.id.otp_view)

        otp.otpListener = object : OTPListener {
            override fun onInteractionListener() {

            }

            override fun onOTPComplete(otp: String) {
                findNavController().navigate(R.id.action_verifyPhoneNum_to_profileSetFragment)
            }

        }
        otp.requestFocusOTP()







        return v
    }


}