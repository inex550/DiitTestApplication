package com.example.diittestapplication.app.adapter

import android.content.res.Resources
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.example.diittestapplication.R
import com.example.diittestapplication.databinding.OrdersListItemBinding
import com.example.diittestapplication.models.Order
import java.text.SimpleDateFormat
import java.util.*

class OrdersAdapter: RecyclerView.Adapter<OrdersAdapter.ViewHolder>() {

    val sdf = SimpleDateFormat("от dd MMMM yyyy, hh:mm", Locale("ru"))

    inner class ViewHolder(
        private val binding: OrdersListItemBinding
    ): RecyclerView.ViewHolder(binding.root) {

        fun bind(order: Order) {
            binding.numberTv.text = "№ ${order.number}"
            binding.dateTv.text = sdf.format(Date(order.datetime))
            binding.deliveryIv.load(order.delivery.icon)
            binding.deliveryTv.text = order.delivery.name
            binding.statusTv.text = order.status.name

            val statusColor = when(order.status.id) {
                2 -> R.color.green
                7 -> R.color.green
                8 -> R.color.red
                else -> R.color.gray
            }

            binding.statusTv.setTextColor(ContextCompat.getColor(binding.root.context, statusColor))
            binding.statusSiv.setImageResource(statusColor)

            binding.priceTv.text = "${order.sum} ₽"
        }
    }


    var orders = listOf<Order>()
        set(value) {
            field = value
            notifyDataSetChanged()
        }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = OrdersListItemBinding.inflate(inflater, parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val order = orders[position]
        holder.bind(order)
    }

    override fun getItemCount(): Int = orders.size
}
