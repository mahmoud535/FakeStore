package com.example.fakestore.ui.home

import android.os.Bundle
import android.view.View
import android.view.inputmethod.EditorInfo
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.fakestore.R
import com.example.fakestore.databinding.FragmentHomeBinding
import com.example.fakestore.domain.model.CategoryType
import com.example.fakestore.domain.model.ProductUIModel
import com.example.fakestore.ui.UiEvent
import com.example.fakestore.ui.customview.LoadingDialog
import com.example.fakestore.ui.customview.snackbar.Snackbom
import com.example.fakestore.ui.customview.snackbar.SnackbomType
import com.example.fakestore.ui.home.adapter.RecyclerViewItemDecoration
import com.example.fakestore.ui.home.adapter.category.CategoryAdapter
import com.example.fakestore.ui.home.adapter.product.ProductAdapter
import com.example.fakestore.ui.home.adapter.product.ProductAdapterCallback
import com.example.fakestore.util.extension.collectWithLifecycle
import com.example.fakestore.util.viewbinding.viewBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

@AndroidEntryPoint
class HomeFragment : Fragment(R.layout.fragment_home), ProductAdapterCallback {
    private val binding by viewBinding(FragmentHomeBinding::bind)
    private val viewModel: HomeViewModel by viewModels()

    private val productAdapter: ProductAdapter by lazy {
        ProductAdapter(this)
    }

    private val categoryAdapter: CategoryAdapter by lazy { CategoryAdapter(this::onCategoryClick) }

    private val loadingDialog: LoadingDialog by lazy {
        LoadingDialog(requireContext())
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setupProductRecyclerView()
        setupCategoryRecyclerView()

        viewLifecycleOwner.collectWithLifecycle(Lifecycle.State.STARTED) {
            launch {
                viewModel.uiState.collect { state ->
                    if (state.isLoading) binding.clGroup.isVisible = false

                    loadingDialog.showLoading(state.isLoading)

                    if (!state.errorMessage.isNullOrEmpty()) {
                        Snackbom.make(
                            requireView(),
                            state.errorMessage,
                            SnackbomType.ERROR
                        ).show()
                    }

                    if (state.list.isNotEmpty()) {
                        binding.clGroup.isVisible = true
                        productAdapter.submitList(state.list.subList(0, 3))
                    }
                }
            }

            launch {
                viewModel.uiEvent.collectLatest { event ->
                    when (event) {
                        is UiEvent.Success -> Snackbom.make(
                            requireView(),
                            event.successMessage,
                            SnackbomType.SUCCESS
                        ).show()


                        is UiEvent.Error -> Snackbom.make(
                            requireView(),
                            event.errorMessage,
                            SnackbomType.ERROR
                        ).show()
                    }
                }
            }
        }

        binding.apply {
            etSearch.setOnEditorActionListener { _, actionId, _ ->
                if (actionId == EditorInfo.IME_ACTION_SEARCH) {
                    println(etSearch.text)
                }
                true
            }

            tvSeeAll.setOnClickListener {

                val productList = viewModel.uiState.value.list
                val action =
                    HomeFragmentDirections.actionHomeFragmentToProductListFragment(
                        productList.toTypedArray(), null
                    )
                findNavController().navigate(action)

            }
        }
    }

    private fun onCategoryClick(categoryType: CategoryType) {
        val action = HomeFragmentDirections.actionHomeFragmentToProductListFragment(
            emptyArray(), category = categoryType.title
        )
        findNavController().navigate(action)
    }

    private fun setupProductRecyclerView() {
        val recyclerView = binding.rvProductList
        recyclerView.layoutManager = LinearLayoutManager(
            requireContext(), LinearLayoutManager.HORIZONTAL, false
        )
        recyclerView.addItemDecoration(RecyclerViewItemDecoration())
        recyclerView.adapter = productAdapter
    }

    private fun setupCategoryRecyclerView() {
        val categoryRecyclerView = binding.rvCategory
        categoryRecyclerView.layoutManager = LinearLayoutManager(
            requireContext(), LinearLayoutManager.HORIZONTAL, false
        )
        categoryRecyclerView.addItemDecoration(RecyclerViewItemDecoration())

        categoryAdapter.submitList(CategoryType.values().toList())
        categoryRecyclerView.adapter = categoryAdapter
    }

    override fun onClickCard(product: ProductUIModel) {
        val action = HomeFragmentDirections.actionHomeFragmentToProductDetailFragment(product)
        findNavController().navigate(action)
    }

    override fun onClickAddToBagButton(product: ProductUIModel) {
        viewModel.addToBasket(productUIModel = product)
    }
}

