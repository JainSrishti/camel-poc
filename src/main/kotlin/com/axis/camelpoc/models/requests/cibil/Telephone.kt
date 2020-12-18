package com.axis.camelpoc.models.requests.cibil

import com.fasterxml.jackson.annotation.JsonProperty

data class Telephone(

	@JsonProperty("TelephoneType")
	val telephoneType: String? = null,

	@JsonProperty("TelephoneNumber")
	val telephoneNumber: String? = null,

	@JsonProperty("TelephoneExtension")
	val telephoneExtension: String? = null
)