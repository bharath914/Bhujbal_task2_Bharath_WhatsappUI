package com.bharath.bharath_instagram

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.net.ConnectivityManager
import android.os.Bundle
import android.view.MenuItem
import android.view.View
import android.widget.ImageView
import android.widget.PopupMenu
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.viewpager2.widget.ViewPager2
import com.bharath.bharath_instagram.fragments.main.ChatsFragment
import com.bharath.bharath_instagram.fragments.main.CommunityFrag
import com.bharath.bharath_instagram.fragments.main.callsFragment
import com.bharath.bharath_instagram.fragments.main.updatesFragment
import com.bharath.bharath_instagram.other.Cons
import com.bharath.bharath_instagram.presentation.adatper.PagerAdapter
import com.bharath.bharath_instagram.presentation.viewmodel.MainViewModel
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    lateinit var tabrow: TabLayout
    lateinit var signOut: ImageView

    lateinit var sp: SharedPreferences

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        sp = getSharedPreferences(Cons.sharedPref, MODE_PRIVATE)

        val mainViewModel = ViewModelProvider(this)[MainViewModel::class.java]

        setContentView(R.layout.activity_main)
        if (!sp.getBoolean(Cons.isUserSignedIn, false)) {
            val intent = Intent(this, SignUpActivity::class.java)
            startActivity(intent)
        } else {

            if (!isNetworkConnected(this)) {
                showNetworkBOx()
            } else {

                val viewPager = findViewById<ViewPager2>(R.id.ViewPager)



                tabrow = findViewById(R.id.tabRow)
                val fragments = listOf<Fragment>(
                    CommunityFrag(mainViewModel),
                    ChatsFragment(mainViewModel),
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
    }

    private fun showNetworkBOx() {
        val builder: AlertDialog.Builder = AlertDialog.Builder(this)
        builder.setTitle("Not Connected")
        builder.setMessage("Please Connect to Internet")
        builder.setPositiveButton(
            "Check Again"
        ) { dialog, which ->
            Networkrestart()
        }
        builder.setNegativeButton("Close App") { dialog, which ->
            finishAffinity()

        }
        builder.create()
        builder.show()
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

    private fun Networkrestart() {
        val intent = Intent(this, MainActivity::class.java)


        startActivity(intent)


    }

    fun isNetworkConnected(context: Context): Boolean {
        val cm = context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        val activeNetwork = cm.activeNetworkInfo
        return activeNetwork != null && activeNetwork.isConnectedOrConnecting
    }
}