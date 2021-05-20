package com.example.diittestapplication.presentation.adapter

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
            binding.iconIv.load(service.icon)
            binding.nameTv.text = service.name
            binding.priceTv.text = service.price.toString()
        }
    }

    var services = listOf<Service>()
        set(value) {
            field = value
            notifyDataSetChanged()
        }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = ServicesListItemBinding.inflate(inflater, parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val service = services[position]
        holder.bind(service)
    }

    override fun getItemCount(): Int = services.size
}