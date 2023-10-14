package com.bharath.bharath_instagram.presentation.adatper.expanded

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.bharath.bharath_instagram.R
import com.bharath.bharath_instagram.data.entity.product.GroupData

class ExpandedGroupAdapter(
    private val context: Context,
) :
    ListAdapter<GroupData, ExpandedGroupAdapter.InnerGroupViewHolder>(ItemDiffUtilCallBack()) {


    inner class InnerGroupViewHolder(itemView: View) : ViewHolder(itemView) {
        val userName: TextView = itemView.findViewById(R.id.Tv_GroupUserName)
        val recycler: RecyclerView = itemView.findViewById(R.id.InnerGroupLayoutRecyclerView)
    }

    private class ItemDiffUtilCallBack : DiffUtil.ItemCallback<GroupData>() {
        override fun areItemsTheSame(oldItem: GroupData, newItem: GroupData): Boolean {
            return oldItem.user_id == newItem.user_id
        }

        override fun areContentsTheSame(oldItem: GroupData, newItem: GroupData): Boolean {
            return oldItem == newItem
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): InnerGroupViewHolder {
        return InnerGroupViewHolder(
            LayoutInflater.from(context).inflate(R.layout.group_inner_recycler_item, parent, false)
        )
    }

    override fun onBindViewHolder(holder: InnerGroupViewHolder, position: Int) {
        val currentItem = getItem(position)
        holder.userName.text = currentItem.name
        val adap = InnerExpandedRecylerAdap(context)
        holder.recycler.layoutManager = LinearLayoutManager(context)
        holder.recycler.adapter = adap
        adap.submitList(currentItem.order_detail)

    }

}


