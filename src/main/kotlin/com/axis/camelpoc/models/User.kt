package com.axis.camelpoc.models

import com.fasterxml.jackson.annotation.JsonInclude
import com.fasterxml.jackson.annotation.JsonProperty

@JsonInclude(JsonInclude.Include.NON_EMPTY)
data class User(@JsonProperty("FNAME") val name: String,
                @JsonProperty("DOB DATE") val dateOfBirth: String,
                @JsonProperty("SEX") val sex: Char,
                @JsonProperty("ADDRESS1") val addressLineOne: String,
                @JsonProperty("STATE") val state: String,
                @JsonProperty("ZIPCODE") val zipCode: String,
                @JsonProperty("PAN_NO") val panNumber: String,
                @JsonProperty("PRODUCT_CODE") val productCode: String,
                @JsonProperty("MOBILE_NO") val mobile: String,
                @JsonProperty("LOAN_AMOUNT") val loan: Long) {

    constructor(name: String, dateOfBirth: String, sex: Char, addressLineOne: String, state: String, zipCode: String, panNumber: String, productCode: String, mobile: String, loan: Long, @JsonProperty("AADHAR_CARD_NO") aadharCardNo: String): this(name, dateOfBirth, sex, addressLineOne, state, zipCode, panNumber, productCode, mobile, loan)

}