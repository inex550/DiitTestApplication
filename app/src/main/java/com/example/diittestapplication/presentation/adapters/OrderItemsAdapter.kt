package com.example.diittestapplication.presentation.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.example.diittestapplication.databinding.OrderItemsListItemBinding
import com.example.diittestapplication.domain.models.OrderItem
import com.example.diittestapplication.utils.format

class OrderItemsAdapter: RecyclerView.Adapter<OrderItemsAdapter.ViewHolder>() {

    inner class ViewHolder(
            private val binding: OrderItemsListItemBinding
    ): RecyclerView.ViewHolder(binding.root) {

        fun bind(orderItem: OrderItem) {
            with (binding) {
                imageIv.load(orderItem.imageUrl)

                nameTv.text = orderItem.name
                priceTv.text = orderItem.price.format()
                quantityTv.text = orderItem.quantity.toString()
                articulTv.text = orderItem.articul
            }
        }
    }

    private val orderItems by lazy {
        arrayListOf<OrderItem>()
    }

    fun setItems(items: List<OrderItem>) {
        orderItems.apply {
            clear()
            addAll(items)
        }

        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        ViewHolder(OrderItemsListItemBinding.inflate(LayoutInflater.from(parent.context), parent, false))

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(orderItems[position])
    }

    override fun getItemCount(): Int = orderItems.size
}