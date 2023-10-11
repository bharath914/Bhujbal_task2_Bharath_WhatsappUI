package com.bharath.bharath_instagram.presentation.adatper

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.bharath.bharath_instagram.R
import com.bharath.bharath_instagram.data.entity.product.GroupData

class GroupsAdapter(val context: Context) :
    ListAdapter<GroupData, GroupsAdapter.GroupViewHolder>(ItemDiffUtilCallback()) {
    inner class GroupViewHolder(itemView: View) : ViewHolder(itemView) {

    }

    private class ItemDiffUtilCallback : DiffUtil.ItemCallback<GroupData>() {
        override fun areItemsTheSame(oldItem: GroupData, newItem: GroupData): Boolean {
            return oldItem.user_id == newItem.user_id
        }

        override fun areContentsTheSame(oldItem: GroupData, newItem: GroupData): Boolean {
            return oldItem == newItem
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): GroupViewHolder {
        return GroupViewHolder(
            LayoutInflater.from(context).inflate(R.layout.group_list_item, parent, false)
        )
    }

    override fun onBindViewHolder(holder: GroupViewHolder, position: Int) {
        val currentItem = getItem(position)
    }

}

