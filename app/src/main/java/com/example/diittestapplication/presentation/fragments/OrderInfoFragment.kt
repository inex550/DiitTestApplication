package com.example.diittestapplication.presentation.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.content.ContentProviderCompat.requireContext
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.arellomobile.mvp.MvpAppCompatFragment
import com.arellomobile.mvp.presenter.InjectPresenter
import com.arellomobile.mvp.presenter.ProvidePresenter
import com.example.diittestapplication.R
import com.example.diittestapplication.presentation.activities.MainActivity
import com.example.diittestapplication.presentation.adapters.OrderItemsAdapter
import com.example.diittestapplication.presentation.adapters.ServicesAdapter
import com.example.diittestapplication.presentation.presenters.OrderInfoPresenter
import com.example.diittestapplication.presentation.views.OrderInfoView
import com.example.diittestapplication.databinding.FragmentOrderInfoBinding
import com.example.diittestapplication.domain.models.*
import com.example.diittestapplication.presentation.App
import com.example.diittestapplication.utils.colorFromStatus
import com.example.diittestapplication.utils.format
import javax.inject.Inject

class OrderInfoFragment: MvpAppCompatFragment(), OrderInfoView {

    private var _binding: FragmentOrderInfoBinding? = null
    private val binding: FragmentOrderInfoBinding get() = _binding!!

    @Inject
    @InjectPresenter
    lateinit var presenter: OrderInfoPresenter

    @ProvidePresenter
    fun providePresenter() = presenter

    private val servicesAdapter = ServicesAdapter()
    private val orderItemsAdapter = OrderItemsAdapter()

    lateinit var order: Order

    override fun onCreate(savedInstanceState: Bundle?) {
        App.INSTANCE.appComponent.inject(this)
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        _binding = FragmentOrderInfoBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        order = arguments!!.getSerializable("order") as Order

        (requireActivity() as MainActivity).updateTitle(order.number)

        presenter.loadOrderInfo(order.id)

        val dividerItemDecoration = DividerItemDecoration(requireContext(), LinearLayoutManager.VERTICAL)

        binding.servicesListRv.addItemDecoration(dividerItemDecoration)
        binding.servicesListRv.adapter = servicesAdapter

        binding.itemsListRv.addItemDecoration(dividerItemDecoration)
        binding.itemsListRv.adapter = orderItemsAdapter
    }

    override fun showContent() {
        binding.orderInfoContent.visibility = View.VISIBLE
    }

    override fun showDeliveryTime(deliveryTime: DeliveryTime) {
        binding.deliveryDateTv.text = deliveryTime.data?.date.orEmpty()
        binding.deliveryTimeTv.text = deliveryTime.data?.time.orEmpty()
    }

    override fun showTopOrderInfo() {
        binding.orderDateTv.text = order.date.format()
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
            servicesAdapter.setServices(services)
    }

    override fun showOrderItems(items: List<OrderItem>) {
        binding.itemsCountTv.text = resources.getQuantityString(R.plurals.items_count_plurals, items.size, items.size)
        orderItemsAdapter.setItems(items)
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