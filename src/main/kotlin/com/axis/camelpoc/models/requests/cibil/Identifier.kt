package com.axis.camelpoc.models.requests.cibil

import com.fasterxml.jackson.annotation.JsonProperty

data class Identifier(

	@JsonProperty("IdNumber")
	val idNumber: String? = null,

	@JsonProperty("IdType")
	val idType: String? = null
)