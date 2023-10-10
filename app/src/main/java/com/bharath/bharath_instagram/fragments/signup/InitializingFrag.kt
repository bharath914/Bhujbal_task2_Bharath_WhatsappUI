package com.bharath.bharath_instagram.fragments.signup

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ProgressBar
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.bharath.bharath_instagram.MainActivity
import com.bharath.bharath_instagram.R
import com.bharath.bharath_instagram.other.Cons

class InitializingFrag : Fragment() {


    lateinit var progress: ProgressBar
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        // Inflate the layout for this fragment
        val v = inflater.inflate(R.layout.fragment_initializing, container, false)
        progress = v.findViewById(R.id.Progress_Indicator)
        val sp = activity?.getSharedPreferences(Cons.sharedPref, AppCompatActivity.MODE_PRIVATE)
        val edit = sp?.edit()

        val handler = Handler()
        handler.postDelayed({
            val intent = Intent(activity, MainActivity::class.java)
            edit?.putBoolean(Cons.isUserSignedIn, true)
            edit?.apply()

            startActivity(intent)

        }, 1000)

        return v

    }


}