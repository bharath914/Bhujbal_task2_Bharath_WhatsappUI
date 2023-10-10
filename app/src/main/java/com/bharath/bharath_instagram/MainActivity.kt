package com.bharath.bharath_instagram

import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.view.MenuItem
import android.view.View
import android.widget.ImageView
import android.widget.PopupMenu
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.viewpager2.widget.ViewPager2
import com.bharath.bharath_instagram.fragments.main.callsFragment
import com.bharath.bharath_instagram.fragments.main.chatsFragment
import com.bharath.bharath_instagram.fragments.main.communityFrag
import com.bharath.bharath_instagram.fragments.main.updatesFragment
import com.bharath.bharath_instagram.other.Cons
import com.bharath.bharath_instagram.presentation.adatper.PagerAdapter
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator

class MainActivity : AppCompatActivity() {

    lateinit var tabrow: TabLayout
    lateinit var signOut: ImageView

    lateinit var sp: SharedPreferences

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        sp = getSharedPreferences(Cons.sharedPref, MODE_PRIVATE)


        setContentView(R.layout.activity_main)
        if (!sp.getBoolean(Cons.isUserSignedIn, false)) {
            val intent = Intent(this, SignUpActivity::class.java)
            startActivity(intent)
        } else {

            val viewPager = findViewById<ViewPager2>(R.id.ViewPager)
            tabrow = findViewById(R.id.tabRow)
            val fragments = listOf<Fragment>(
                communityFrag(),
                chatsFragment(),
                updatesFragment(),
                callsFragment()
            )
            signOut = findViewById(R.id.signOutButton)
            val adapter = PagerAdapter(this, fragments)
            viewPager.adapter = adapter
            TabLayoutMediator(tabrow, viewPager) { tab, pos ->
                when (pos) {
                    0 -> tab.icon = getDrawable(R.drawable.groups_icon)
                    1 -> tab.text = "Chats"
                    2 -> tab.text = "Updates"
                    3 -> tab.text = "Calls"
                }

            }.attach()

            signOut.setOnClickListener {
                showPopupMenu(it)
            }

        }
    }

    override fun onBackPressed() {
        super.onBackPressed()
        finishAffinity()
    }

    fun showPopupMenu(view: View) {
        val popupMenu = PopupMenu(this, view)
        popupMenu.inflate(R.menu.menu_res) // Replace with your menu resource

        popupMenu.setOnMenuItemClickListener { item: MenuItem ->
            // Handle menu item clicks here
            when (item.itemId) {
                R.id.menu_item_1 -> {
                    // Do something when menu item 1 is clicked
                    showAlertBox()
                    true
                }


                else -> false
            }
        }

        popupMenu.show()
    }

    private fun showAlertBox() {
        val builder: AlertDialog.Builder = AlertDialog.Builder(this)
        builder.setTitle("Are You Sure?")
        builder.setMessage("Logging out clears all the data")
        builder.setPositiveButton(
            "Ok"
        ) { dialog, which ->
            restart()
        }
        builder.create()
        builder.show()
    }

    private fun restart() {
        val intent = Intent(this, SignUpActivity::class.java)
        val edit = sp.edit()
        edit.putBoolean(Cons.isUserSignedIn, false)
        edit.apply()
        startActivity(intent)


    }
}