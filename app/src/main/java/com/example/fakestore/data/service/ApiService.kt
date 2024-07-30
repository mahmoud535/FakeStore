package com.example.fakestore.data.service

import com.example.fakestore.data.model.ProductDto
import com.example.fakestore.util.ApiResponse
import com.example.fakestore.util.Constants
import com.example.fakestore.util.extension.safeApiCall
import io.ktor.client.HttpClient
import io.ktor.client.request.get
import io.ktor.client.request.url
import io.ktor.http.appendPathSegments
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class ApiService @Inject constructor(
    private val client: HttpClient
) {
    suspend fun getProductList(): Flow<ApiResponse<List<ProductDto>>> = safeApiCall {
        client.get(Constants.PRODUCTS)
    }

    suspend fun getProductByID(id: Int = 5): Flow<ApiResponse<ProductDto>> = safeApiCall {
        client.get {
            url(path = Constants.PRODUCTS) {
                appendPathSegments("$id")
            }
        }
    }

    suspend fun getProductListByCategory(category: String): Flow<ApiResponse<List<ProductDto>>> {
        return safeApiCall {
            client.get {
                url(path = Constants.CATEGORY) {
                    appendPathSegments(category)
                }
            }
        }
    }
}