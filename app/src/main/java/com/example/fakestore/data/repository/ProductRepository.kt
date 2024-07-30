package com.example.fakestore.data.repository

import com.example.fakestore.data.service.ApiService
import com.example.fakestore.mapper.toProductUIModel
import com.example.fakestore.domain.model.ProductUIModel
import com.example.fakestore.util.Resource
import com.example.fakestore.util.extension.mapOnSuccess
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class ProductRepository @Inject constructor(
    private val apiService: ApiService
) {

    suspend fun getProducts(): Flow<Resource<List<ProductUIModel>>> {
        return apiService.getProductList().mapOnSuccess { productList ->
            productList.toProductUIModel()
        }
    }

    suspend fun getProductByID(id: Int): Flow<Resource<ProductUIModel>> {
        return apiService.getProductByID(id = id).mapOnSuccess { product ->
            product.toProductUIModel()
        }
    }

    suspend fun getProductListByCategory(category: String): Flow<Resource<List<ProductUIModel>>> {
        return apiService.getProductListByCategory(category).mapOnSuccess { productList ->
            productList.toProductUIModel()
        }
    }
}
