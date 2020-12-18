package com.axis.camelpoc.models.requests.cibil

import com.fasterxml.jackson.annotation.JsonProperty
import com.fasterxml.jackson.annotation.JsonPropertyOrder
import com.fasterxml.jackson.annotation.JsonRootName

@JsonRootName("DCRequest")
@JsonPropertyOrder("authentication", "requestInfo", "applicant", "applicationData", "finalTraceLevel")
data class DCRequest(

        @JsonProperty("FinalTraceLevel")
        var finalTraceLevel: String? = null,

        @JsonProperty("Authentication")
        val authentication: Authentication?= null,

        @JsonProperty("ApplicationData")
        val applicationData: ApplicationData? = null,

        @JsonProperty("RequestInfo")
        val requestInfo: RequestInfo? = null,

        @JsonProperty("Applicant")
        val applicant: List<Applicant?>? = null
)