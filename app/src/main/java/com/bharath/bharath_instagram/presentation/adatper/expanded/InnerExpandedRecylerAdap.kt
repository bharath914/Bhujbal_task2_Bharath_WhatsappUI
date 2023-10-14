package com.bharath.bharath_instagram.presentation.adatper.expanded

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.bharath.bharath_instagram.R
import com.bharath.bharath_instagram.data.entity.product.OrderDetail

class InnerExpandedRecylerAdap(
    val context: Context,
) :
    ListAdapter<OrderDetail, InnerExpandedRecylerAdap.InnerExpandedViewHolder>(ItemDiffUtilCallBack()) {

    inner class InnerExpandedViewHolder(itemView: View) : ViewHolder(itemView) {
        val productName: TextView = itemView.findViewById(R.id.innerGroupProductItem)
        val productQuantity: TextView = itemView.findViewById(R.id.itemQuantity)
        fun bind(orderDetail: OrderDetail) {
            productName.text = orderDetail.product_name
            productQuantity.text = orderDetail.product_qty
        }
    }

    private class ItemDiffUtilCallBack : DiffUtil.ItemCallback<OrderDetail>() {
        override fun areItemsTheSame(oldItem: OrderDetail, newItem: OrderDetail): Boolean {
            return oldItem.user_id == newItem.user_id
        }

        override fun areContentsTheSame(oldItem: OrderDetail, newItem: OrderDetail): Boolean {
            return oldItem == newItem
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): InnerExpandedViewHolder {
        return InnerExpandedViewHolder(
            LayoutInflater.from(context).inflate(R.layout.group_inner_item, parent, false)
        )
    }

    override fun onBindViewHolder(holder: InnerExpandedViewHolder, position: Int) {
        val currentItem = getItem(position)
        holder.bind(currentItem)
    }
}