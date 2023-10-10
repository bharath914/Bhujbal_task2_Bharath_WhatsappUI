package com.bharath.bharath_instagram


import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.bharath.bharath_instagram.fragments.signup.LanguageFragment


class SignUpActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sign_up)
    }

    override fun onBackPressed() {

        val fragment = supportFragmentManager.findFragmentById(R.id.nav_host_fragment)
        if (fragment is LanguageFragment) {

            finishAffinity()
        } else {
            super.onBackPressed()

        }
    }

}