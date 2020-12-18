package com.axis.camelpoc.models.requests.cibil

import com.fasterxml.jackson.annotation.JsonProperty

data class RequestInfo(

	@JsonProperty("SolutionSetId")
	val solutionSetId: String? = null,

	@JsonProperty("ExecuteLatestVersion")
	val executeLatestVersion: String? = null,

	@JsonProperty("ExecutionMode")
	val executionMode: String? = null
)