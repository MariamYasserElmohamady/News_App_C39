package com.route.news_app_c39.api.modal.sourcesResponse

import com.google.gson.annotations.SerializedName

data class SourcesResponse(

	@field:SerializedName("sources")
	val sources: List<Source?>? = null,

	@field:SerializedName("status")
	val status: String? = null,
	@field:SerializedName("code")
	val code: String? = null,
	@field:SerializedName("message")
	val message: String? = null,
)