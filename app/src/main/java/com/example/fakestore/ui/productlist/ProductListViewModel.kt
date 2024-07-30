package com.example.fakestore.ui.productlist

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.fakestore.data.repository.ProductRepository
import com.example.fakestore.domain.model.ProductUIModel
import com.example.fakestore.util.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ProductListViewModel @Inject constructor(
    private val repository: ProductRepository
) : ViewModel() {

    private val _uiState = MutableStateFlow<Resource<List<ProductUIModel>>>(Resource.Loading)
    val uiState = _uiState.asStateFlow()

    fun getProductListByCategory(category: String) {
        viewModelScope.launch {
            repository.getProductListByCategory(category).collect { result ->
                _uiState.emit(result)
            }
        }
    }
}