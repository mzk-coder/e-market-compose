package com.mzk.samplee_market.data.models

data class OrderRequest(val products: List<Product>, val delivery_address: String)
