package com.axis.camelpoc.models.requests.cibil

import com.fasterxml.jackson.annotation.JsonProperty

data class ApplicationData(

	@JsonProperty("DSTuNtcFlag")
	val dSTuNtcFlag: String? = null,

	@JsonProperty("MFICenterReferenceNo")
	val mFICenterReferenceNo: String? = null,

	@JsonProperty("MFIEnquiryAmount")
	val mFIEnquiryAmount: String? = null,

	@JsonProperty("Amount")
	val amount: String? = null,

	@JsonProperty("IDVerificationFlag")
	val iDVerificationFlag: String? = null,

	@JsonProperty("Purpose")
	val purpose: String? = null,

	@JsonProperty("MFIBureauFlag")
	val mFIBureauFlag: String? = null,

	@JsonProperty("CibilBureauFlag")
	val cibilBureauFlag: String? = null,

	@JsonProperty("GSTStateCode")
	val gSTStateCode: String,

	@JsonProperty("MFILoanPurpose")
	val mFILoanPurpose: String? = null,

	@JsonProperty("MFIBranchReferenceNo")
	val mFIBranchReferenceNo: String? = null,

	@JsonProperty("ConsumerConsentForUIDAIAuthentication")
	val consumerConsentForUIDAIAuthentication: String? = null,

	@JsonProperty("ScoreType")
	val scoreType: String? = null,

	@JsonProperty("NTCProductType")
	val nTCProductType: String? = null,

	@JsonProperty("MemberCode")
	val memberCode: String? = null,

	@JsonProperty("FormattedReport")
	val formattedReport: String? = null,

	@JsonProperty("Password")
	val password: String? = null
)