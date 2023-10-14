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
import com.bharath.bharath_instagram.data.entity.product.OrderDetail

class ProductListAdatper(val context: Context) :
    ListAdapter<OrderDetail, ProductListAdatper.ProductViewHolder>(ItemDiffCallback()) {


    inner class ProductViewHolder(itemview: View) : ViewHolder(itemview) {
        val ProductName: TextView = itemview.findViewById(R.id.NameOfTheProduct)
        val Address: TextView = itemview.findViewById(R.id.Address)
        val date: TextView = itemview.findViewById(R.id.DateOfTheOrder)
        val quantity: TextView = itemview.findViewById(R.id.Quantity)

        //        val CustomerName: TextView = itemview.findViewById(R.id.NameOfTheCustomer)
        val Price: TextView = itemview.findViewById(R.id.PriceOfTheProduct)
        val OrderStatus: TextView = itemview.findViewById(R.id.OrderStatus)


        fun bindItem(orderDetail: OrderDetail) {
            ProductName.text = orderDetail.product_name
            Address.text = orderDetail.address

            date.text = orderDetail.order_date.substring(0, 10)


//                "Date : ${orderDetail.order_date.substring(0, 10)}"
            quantity.text =
                String.format(context.getString(R.string.qty_place_holder), orderDetail.quantity)

            Price.text = String.format(
                context.getString(R.string.price_place_holder),
                orderDetail.grand_total
            )
            OrderStatus.text = orderDetail.order_status

        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProductViewHolder {
        return ProductViewHolder(
            LayoutInflater.from(context).inflate(R.layout.product_list_item, parent, false)
        )
    }


    private class ItemDiffCallback : DiffUtil.ItemCallback<OrderDetail>() {

        override fun areItemsTheSame(oldItem: OrderDetail, newItem: OrderDetail): Boolean {
            return oldItem.user_id == newItem.user_id // Replace 'id' with your unique identifier property
        }

        override fun areContentsTheSame(oldItem: OrderDetail, newItem: OrderDetail): Boolean {
            return oldItem == newItem // Replace with your equality check logic
        }
    }


    override fun onBindViewHolder(holder: ProductViewHolder, position: Int) {
        val currentItem = getItem(position)
        holder.bindItem(currentItem)

    }
}