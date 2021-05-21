package com.example.diittestapplication.presentation.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.arellomobile.mvp.presenter.InjectPresenter
import com.example.diittestapplication.presentation.App
import com.example.diittestapplication.presentation.activities.MainActivity
import com.example.diittestapplication.presentation.adapters.OrdersAdapter
import com.example.diittestapplication.presentation.presenters.OrdersPresenter
import com.example.diittestapplication.presentation.screens.Screens
import com.example.diittestapplication.presentation.views.OrdersView
import com.example.diittestapplication.databinding.FragmentOrdersBinding
import com.example.diittestapplication.domain.models.Order
import com.github.terrakok.cicerone.Router
import javax.inject.Inject

class OrdersFragment: Fragment(), OrdersView, OrdersAdapter.OrderSelectListener {

    private var _binding: FragmentOrdersBinding? = null
    private val binding: FragmentOrdersBinding get() = _binding!!

    private val ordersAdapter = OrdersAdapter(this)

    @Inject
    @InjectPresenter
    lateinit var presenter: OrdersPresenter

    @Inject
    lateinit var router: Router

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        App.INSTANCE.appComponent.inject(this)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentOrdersBinding.inflate(inflater, container, false)

        (requireActivity() as MainActivity).updateTitle("Мои заказы")

        binding.ordersListRv.adapter = ordersAdapter

        if (ordersAdapter.orders.isEmpty())
            presenter.loadOrders()

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
        ordersAdapter.setOrders(orders)
    }

    override fun onOrderSelected(order: Order) {
        val bundle = Bundle().apply {
            putSerializable("order", order)
        }

        router.navigateTo(Screens.orderInfoScreen(bundle))
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}