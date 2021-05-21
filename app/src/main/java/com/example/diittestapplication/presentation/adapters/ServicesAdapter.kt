package com.example.diittestapplication.presentation.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.example.diittestapplication.databinding.ServicesListItemBinding
import com.example.diittestapplication.domain.models.Service

class ServicesAdapter: RecyclerView.Adapter<ServicesAdapter.ViewHolder>() {

    inner class ViewHolder(
            private val binding: ServicesListItemBinding
    ): RecyclerView.ViewHolder(binding.root) {

        fun bind(service: Service) {
            with (binding) {
                iconIv.load(service.icon)
                nameTv.text = service.name
                priceTv.text = service.price.toString()
            }
        }
    }

    private val services by lazy {
        arrayListOf<Service>()
    }

    fun setServices(items: List<Service>) {
        services.apply {
            clear()
            addAll(items)
        }

        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        ViewHolder(ServicesListItemBinding.inflate(LayoutInflater.from(parent.context), parent, false))

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(services[position])
    }

    override fun getItemCount(): Int = services.size
}