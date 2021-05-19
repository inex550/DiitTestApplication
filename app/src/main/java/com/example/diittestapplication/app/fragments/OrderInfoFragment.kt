package com.example.diittestapplication.app.fragments

import android.graphics.Color
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.diittestapplication.app.activities.MainActivity
import com.example.diittestapplication.app.adapter.OrderItemsAdapter
import com.example.diittestapplication.app.adapter.ServicesAdapter
import com.example.diittestapplication.app.presenters.OrderInfoPresenter
import com.example.diittestapplication.app.views.OrderInfoView
import com.example.diittestapplication.databinding.FragmentOrderInfoBinding
import com.example.diittestapplication.models.*
import com.example.diittestapplication.utils.colorFromStatus
import com.example.diittestapplication.utils.pluralItemsCount
import com.example.diittestapplication.utils.timeToFormatedString

class OrderInfoFragment(
        private val order: Order
): Fragment(), OrderInfoView {

    private var _binding: FragmentOrderInfoBinding? = null
    private val binding: FragmentOrderInfoBinding get() = _binding!!

    private lateinit var presenter: OrderInfoPresenter

    private val servicesAdapter = ServicesAdapter()
    private val orderItemsAdapter = OrderItemsAdapter()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        _binding = FragmentOrderInfoBinding.inflate(inflater, container, false)

        (requireActivity() as MainActivity).updateTitle(order.number)

        presenter = OrderInfoPresenter(this)
        presenter.loadOrderInfo(order.id)

        val dividerItemDecoration = DividerItemDecoration(requireContext(), LinearLayoutManager.VERTICAL)

        binding.servicesListRv.addItemDecoration(dividerItemDecoration)
        binding.servicesListRv.layoutManager = LinearLayoutManager(requireContext())
        binding.servicesListRv.adapter = servicesAdapter

        binding.itemsListRv.addItemDecoration(dividerItemDecoration)
        binding.itemsListRv.layoutManager = LinearLayoutManager(requireContext())
        binding.itemsListRv.adapter = orderItemsAdapter

        return binding.root
    }

    override fun showContent() {
        binding.orderInfoContent.visibility = View.VISIBLE
    }

    override fun showDeliveryTime(deliveryTime: DeliveryTime) {
        binding.deliveryDateTv.text = deliveryTime.data!!.date
        binding.deliveryTimeTv.text = deliveryTime.data.time
    }

    override fun showTopOrderInfo() {
        binding.orderDateTv.text = order.datetime.timeToFormatedString()
        binding.statusTv.text = order.status.name
        binding.statusTv.setTextColor(ContextCompat.getColor(requireContext(), order.status.id.colorFromStatus()))
        binding.deliveryTv.text = order.delivery.name
    }

    override fun showAddress(address: String) {
        if (order.delivery.name.contains("Самовывоз")) {
            binding.addressTitleTv.text = "Адрес пункта выдачи"
            binding.addressTv.setTextColor(ContextCompat.getColor(requireContext(), android.R.color.holo_blue_dark))
        }

        binding.addressTv.text = address
    }

    override fun showOtherCenterInfo(orderInfo: OrderInfo) {
        binding.paymentTv.text = orderInfo.payment.name ?: ""
        binding.bonusCardTv.text = orderInfo.bonusCard
        binding.shopTv.text = orderInfo.shop.name ?: ""
    }

    override fun showOrderSum(amount: Amount) {
        if (amount.bonuses != 0) {
            binding.bonusesLl.visibility = View.VISIBLE
            binding.bonusesTv.text = amount.bonuses.toString()
        }

        if (amount.discount != 0) {
            binding.discountLl.visibility = View.VISIBLE
            binding.discountTv.text = amount.discount.toString()
        }

        binding.totalSumTv.text = amount.total.toString()
    }

    override fun showServices(services: List<Service>?) {
        if (services == null || services.isEmpty())
            binding.servicesTitle.visibility = View.GONE
        else
            servicesAdapter.services = services
    }

    override fun showOrderItems(items: List<OrderItem>) {
        binding.itemsCountTv.text = items.size.pluralItemsCount()
        orderItemsAdapter.items = items
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

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}