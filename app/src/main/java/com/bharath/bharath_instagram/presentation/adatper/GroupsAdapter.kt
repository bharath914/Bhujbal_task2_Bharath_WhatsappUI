package com.bharath.bharath_instagram.presentation.adatper

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.bharath.bharath_instagram.R
import com.bharath.bharath_instagram.data.entity.product.Data

class GroupsAdapter(val context: Context) :
    ListAdapter<Data, GroupsAdapter.GroupViewHolder>(ItemDiffUtilCallback()) {
    inner class GroupViewHolder(itemView: View) : ViewHolder(itemView) {

        val groupName: TextView = itemView.findViewById(R.id.GroupName)
        val orderCount: TextView = itemView.findViewById(R.id.OrderCountGroup)

        fun bindIt(data: Data) {
            groupName.text = data.group_name
            orderCount.text =
                String.format(context.getString(R.string.count_placeHolder), data.count)
        }
    }

    private class ItemDiffUtilCallback : DiffUtil.ItemCallback<Data>() {
        override fun areItemsTheSame(oldItem: Data, newItem: Data): Boolean {
            return oldItem.group_id == newItem.group_id
        }

        override fun areContentsTheSame(oldItem: Data, newItem: Data): Boolean {
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
        holder.bindIt(currentItem)

    }

}

