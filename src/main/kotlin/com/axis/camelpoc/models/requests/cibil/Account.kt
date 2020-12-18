package com.axis.camelpoc.models.requests.cibil

import com.fasterxml.jackson.annotation.JsonProperty

data class Account(

	@JsonProperty("AccountNumber")
	val accountNumber: String? = null
)