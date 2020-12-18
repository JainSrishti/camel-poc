package com.axis.camelpoc.models.requests.cibil

import com.fasterxml.jackson.annotation.JsonProperty
import javax.xml.bind.annotation.XmlElement

data class Address(

	@JsonProperty("AddressLine3")
	val addressLine3: String? = null,

	@JsonProperty("ResidenceType")
	val residenceType: String? = null,

	@JsonProperty("AddressLine2")
	val addressLine2: String? = null,

	@JsonProperty("AddressLine1")
	val addressLine1: String? = null,

	@JsonProperty("AddressLine5")
	val addressLine5: String? = null,

	@JsonProperty("AddressLine4")
	val addressLine4: String? = null,

	@JsonProperty("StateCode")
	val stateCode: String? = null,

	@JsonProperty("City")
	val city: String? = null,

	@JsonProperty("AddressType")
	val addressType: String? = null,

	@JsonProperty("PinCode")
	val pinCode: String? = null
)