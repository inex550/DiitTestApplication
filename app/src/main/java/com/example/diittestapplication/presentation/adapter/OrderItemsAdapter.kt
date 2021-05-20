package com.example.diittestapplication.presentation.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.diittestapplication.databinding.OrderItemsListItemBinding
import com.example.diittestapplication.domain.models.OrderItem
import com.example.diittestapplication.utils.format

class OrderItemsAdapter: RecyclerView.Adapter<OrderItemsAdapter.ViewHolder>() {

    inner class ViewHolder(
            private val binding: OrderItemsListItemBinding
    ): RecyclerView.ViewHolder(binding.root) {

        fun bind(orderItem: OrderItem) {
            binding.imageIv.load(orderItem.imageUrl)

            binding.nameTv.text = orderItem.name
            binding.priceTv.text = orderItem.price.format()
            binding.quantityTv.text = orderItem.quantity.toString()
            binding.articulTv.text = orderItem.articul
        }
    }

    var items = listOf<OrderItem>()
        set(value) {
            field = value
            notifyDataSetChanged()
        }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = OrderItemsListItemBinding.inflate(inflater, parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = items[position]
        holder.bind(item)
    }

    override fun getItemCount(): Int = items.size
}