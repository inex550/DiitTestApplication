package com.example.diittestapplication.presentation.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.example.diittestapplication.databinding.OrdersListItemBinding
import com.example.diittestapplication.domain.models.Order
import com.example.diittestapplication.utils.colorFromStatus
import com.example.diittestapplication.utils.format
import com.example.diittestapplication.utils.timeToFormatedString

class OrdersAdapter(
        private val listener: OrderSelectListener
): RecyclerView.Adapter<OrdersAdapter.ViewHolder>() {

    inner class ViewHolder(
        private val binding: OrdersListItemBinding
    ): RecyclerView.ViewHolder(binding.root) {

        init {
            binding.selectBtn.setOnClickListener {
                val order = orders[adapterPosition]
                listener.onOrderSelected(order)
            }
        }

        fun bind(order: Order) {
            binding.numberTv.text = "№ ${order.number}"
            binding.dateTv.text = order.datetime.timeToFormatedString()
            binding.deliveryIv.load(order.delivery.icon)
            binding.deliveryTv.text = order.delivery.name
            binding.statusTv.text = order.status.name

            val statusColor = order.status.id.colorFromStatus()

            binding.statusTv.setTextColor(ContextCompat.getColor(binding.root.context, statusColor))
            binding.statusSiv.setImageResource(statusColor)

            binding.priceTv.text = "${order.sum.format()} ₽"
        }
    }

    interface OrderSelectListener {
        fun onOrderSelected(order: Order)
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
