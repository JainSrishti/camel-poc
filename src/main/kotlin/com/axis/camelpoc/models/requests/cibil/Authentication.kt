package com.axis.camelpoc.models.requests.cibil


import com.fasterxml.jackson.annotation.JsonProperty

data class Authentication(

	@JsonProperty("UserId")
	val userId: String,

	@JsonProperty("Password")
	val password: String
)