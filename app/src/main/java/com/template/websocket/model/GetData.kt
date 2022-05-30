package com.template.websocket.model

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class GetData(

	@Json(name = "data")
	val data: Data1,

	val key: String,

	val status: Int
)

@JsonClass(generateAdapter = true)
data class Data1(

	@Json(name = "driver_id")
	val driverId: Int,

	val address: String,

	val phone: String,

	val addressId: Int,

	val id: Int,

	val info: String,

	val status: Int
)
