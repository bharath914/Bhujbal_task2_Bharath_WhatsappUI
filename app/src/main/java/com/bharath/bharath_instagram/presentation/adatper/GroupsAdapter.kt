package com.bharath.bharath_instagram.presentation.adatper

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.bharath.bharath_instagram.R
import com.bharath.bharath_instagram.data.entity.product.Data
import com.bharath.bharath_instagram.presentation.adatper.expanded.ExpandedGroupAdapter

class GroupsAdapter(val context: Context) :
    ListAdapter<Data, GroupsAdapter.GroupViewHolder>(ItemDiffUtilCallback()) {
    inner class GroupViewHolder(itemView: View) : ViewHolder(itemView) {

        val groupName: TextView = itemView.findViewById(R.id.GroupName)
        val orderCount: TextView = itemView.findViewById(R.id.OrderCountGroup)
        val groupItemView: CardView = itemView.findViewById(R.id.GroupItemView)
        val expandIcon: ImageView = itemView.findViewById(R.id.ExpandIcon)
        val expandedLayout: ConstraintLayout = itemView.findViewById(R.id.ExpandedLayout)
        val expandedREcyclerview: RecyclerView = itemView.findViewById(R.id.ExpandedRecyclerView)

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
        val current = getItem(position)
        holder.bindIt(current)
        val currentItem = current.toExpandableItem()
        currentItem.groupData
        holder.groupItemView.setOnClickListener {
            currentItem.isExpanded = !currentItem.isExpanded
            holder.expandIcon.setImageResource(
                if (currentItem.isExpanded) R.drawable.expand_less_24px else R.drawable.expand_more
            )
            if (currentItem.isExpanded) {
                val adap = ExpandedGroupAdapter(context)
                holder.expandedREcyclerview.layoutManager = LinearLayoutManager(context)
                holder.expandedREcyclerview.adapter = adap
                adap.submitList(currentItem.groupData)
                holder.expandedLayout.visibility = View.VISIBLE
            } else {

                holder.expandedLayout.visibility = View.GONE
            }

        }

    }

}

