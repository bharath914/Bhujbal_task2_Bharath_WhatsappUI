package com.bharath.bharath_instagram.presentation.adatper

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.recyclerview.widget.RecyclerView
import com.bharath.bharath_instagram.R
import com.bharath.bharath_instagram.data.local.LanguageInfo
import com.google.android.material.radiobutton.MaterialRadioButton

class LanguageAdapter(
    val context: Context,
    val languageList: ArrayList<LanguageInfo>,
    private val listener: RecyclerViewClickListener,
) :
    RecyclerView.Adapter<LanguageAdapter.MyViewHolder>() {
    inner class MyViewHolder(itemview: View) : RecyclerView.ViewHolder(itemview) {
        val box: MaterialRadioButton = itemview.findViewById(R.id.radioButton)
        val primary: TextView = itemview.findViewById(R.id.primaryLanguage)
        val secondary: TextView = itemview.findViewById(R.id.secondaryLanguage)
        val layout: ConstraintLayout = itemview.findViewById(R.id.LanguageItem)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        return MyViewHolder(
            LayoutInflater.from(context).inflate(R.layout.language_item, null, false)
        )
    }

    override fun getItemCount(): Int {
        return languageList.size
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val currentItem = languageList[position]
        holder.primary.text = currentItem.primary
        holder.secondary.text = currentItem.secondary

        if (position == 0) {
            holder.box.isChecked = true
        }
        holder.box.setOnClickListener {
            if (position!=0) {

                listener.onItemClick(position)

            }
        }
        holder.layout.setOnClickListener {
            listener.onItemClick(position)
        }

    }


}

interface RecyclerViewClickListener {
    fun onItemClick(position: Int)
}
