package com.axis.camelpoc.models

import com.fasterxml.jackson.annotation.JsonIgnoreProperties

import com.fasterxml.jackson.annotation.JsonProperty

@JsonIgnoreProperties(ignoreUnknown = true)
data class User(@JsonProperty("FNAME") val name: String,
                @JsonProperty("DOB_DATE") val dateOfBirth: String,
                @JsonProperty("SEX") val sex: String,
                @JsonProperty("ADDRESS1") val addressLineOne: String,
                @JsonProperty("STATE") val state: String,
                @JsonProperty("ZIPCODE") val zipCode: String,
                @JsonProperty("PAN_NO") val panNumber: String,
                @JsonProperty("PRODUCT_CODE") val productCode: String,
                @JsonProperty("MOBILE_NO") val mobile: String,
                @JsonProperty("LOAN_AMOUNT") val loan: String) {

    override fun toString(): String {
        return "User(name='$name', " +
                "dateOfBirth='$dateOfBirth', " +
                "sex='$sex', " +
                "addressLineOne='$addressLineOne', " +
                "state='$state', " +
                "zipCode='$zipCode', " +
                "panNumber='$panNumber', " +
                "productCode='$productCode', " +
                "mobile='$mobile', " +
                "loan='$loan')"
    }
}