package com.example.fakestore.ui.payment

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.example.fakestore.R
import com.example.fakestore.databinding.FragmentPaymentSuccessBinding
import com.example.fakestore.util.viewbinding.viewBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class PaymentSuccessFragment : Fragment(R.layout.fragment_payment_success) {

    private val binding by viewBinding(FragmentPaymentSuccessBinding::bind)

    private val viewModel by viewModels<PaymentViewModel>()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel.deleteAll()

        binding.apply {
            btnGoBackHome.setOnClickListener {
                findNavController().popBackStack(R.id.basket_nav_graph, true)
            }
        }
    }
}