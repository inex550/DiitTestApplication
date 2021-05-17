package com.example.diittestapplication.app.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.diittestapplication.app.adapter.OrdersAdapter
import com.example.diittestapplication.app.presenters.OrdersPresenter
import com.example.diittestapplication.app.views.OrdersView
import com.example.diittestapplication.databinding.FragmentOrdersBinding
import com.example.diittestapplication.models.Order

class OrdersFragment: Fragment(), OrdersView {

    private var _binding: FragmentOrdersBinding? = null
    private val binding: FragmentOrdersBinding get() = _binding!!

    private val ordersAdapter = OrdersAdapter()

    private lateinit var presenter: OrdersPresenter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentOrdersBinding.inflate(inflater, container, false)

        presenter = OrdersPresenter(this)

        binding.ordersListRv.layoutManager = LinearLayoutManager(requireContext())
        binding.ordersListRv.adapter = ordersAdapter

        return binding.root
    }

    override fun showLoading() {
        binding.loadingPb.visibility = View.VISIBLE
    }

    override fun hideLoading() {
        binding.loadingPb.visibility = View.GONE
    }

    override fun showLoadingError(error: String) {
        Toast.makeText(requireContext(), error, Toast.LENGTH_SHORT).show()
    }

    override fun showOrders(orders: List<Order>) {
        ordersAdapter.orders = orders
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}