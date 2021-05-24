package com.example.diittestapplication.presentation.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.example.diittestapplication.databinding.OrdersListItemBinding
import com.example.diittestapplication.domain.models.Order
import com.example.diittestapplication.utils.colorFromStatus
import com.example.diittestapplication.utils.format

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
            with (binding) {
                numberTv.text = "№ ${order.number}"
                dateTv.text = order.date.format()
                deliveryIv.load(order.delivery.icon)
                deliveryTv.text = order.delivery.name
                statusTv.text = order.status.name

                val statusColor = order.status.id.colorFromStatus()

                statusTv.setTextColor(ContextCompat.getColor(binding.root.context, statusColor))
                statusSiv.setImageResource(statusColor)

                priceTv.text = "${order.sum.format()} ₽"
            }
        }
    }

    interface OrderSelectListener {
        fun onOrderSelected(order: Order)
    }


    private val orders by lazy {
        arrayListOf<Order>()
    }

    fun setOrders(items: List<Order>) {
        orders.apply {
            clear()
            addAll(items)
        }

        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        ViewHolder(OrdersListItemBinding.inflate(LayoutInflater.from(parent.context), parent, false))

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(orders[position])
    }

    override fun getItemCount(): Int = orders.size
}
