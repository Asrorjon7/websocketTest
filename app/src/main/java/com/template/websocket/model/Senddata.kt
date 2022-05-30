package com.template.websocket.model

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class Senddata(
	@Json(name = "data")
	val data: Data ,
	val key: String,
	val status: Int
)

@JsonClass(generateAdapter = true)
data class Data(
	@Json(name = "token")
	val token: String
)
