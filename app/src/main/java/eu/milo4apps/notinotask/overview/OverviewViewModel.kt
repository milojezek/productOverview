package eu.milo4apps.notinotask.overview

import android.util.Log
import android.widget.ImageButton
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import eu.milo4apps.notinotask.R
import eu.milo4apps.notinotask.network.Product
import eu.milo4apps.notinotask.network.ProductApi
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

enum class ProductApiStatus { LOADING, ERROR, DONE }

class OverviewViewModel : ViewModel() {

    private val _status = MutableLiveData<ProductApiStatus>()
    val status: LiveData<ProductApiStatus> = _status

    private val _products = MutableLiveData<List<Product>>()
    val products: LiveData<List<Product>> = _products

    init {
        getProducts()
    }


    private fun getProducts() {
        viewModelScope.launch {
            try {
                _status.value = ProductApiStatus.LOADING
                val listResult = ProductApi.retrofitService.getResponse().body()?.vpProductByIds
                _products.value = listResult!!
                // _status.value = "Success: ${listResult?.size} products retrieved"
                _status.value = ProductApiStatus.DONE
                formatProductsInfo()
            } catch (e: Exception) {
                _status.value = ProductApiStatus.ERROR
                _products.value = listOf()
            }
        }
    }

    private fun formatProductsInfo() {
        for (item in _products.value!!) {
            item.price.value = item.price.value + " Kč"
            _hearts.value?.add(false)
        }
    }
}
