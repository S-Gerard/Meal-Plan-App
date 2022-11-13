package com.example.cupcake.model

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import java.text.SimpleDateFormat
import java.util.*

private const val PRICE_PER_MEAL = 6.50

class OrderViewModel : ViewModel() {
    private val _quantity = MutableLiveData<Int>()
    val quantity: LiveData<Int> = _quantity

    private val _type = MutableLiveData<String>()
    val type: LiveData<String> = _type

    private val _subscription = MutableLiveData<Int>()
    val subscription: LiveData<Int> = _subscription

    private val _price = MutableLiveData<Double>()
    val price: LiveData<Double> = _price

    fun setQuantity(numberMeals: Int) {
        _quantity.value = numberMeals
        updatePrice()
    }

    fun setType(mealType: String) {
        _type.value = mealType
    }

    fun setSubscription(subscription: Int) {
        _subscription.value = subscription
    }

    fun hasNoTypeSet(): Boolean {
        return _type.value.isNullOrEmpty()
    }


    fun resetOrder() {
        _quantity.value = 0
        _type.value = ""
        _subscription.value = 0
        _price.value = 0.0
    }

    init {
        resetOrder()
    }

    private fun updatePrice() {
        _price.value = ((quantity.value ?: 0) * PRICE_PER_MEAL) * (subscription.value ?: 1)
    }

}