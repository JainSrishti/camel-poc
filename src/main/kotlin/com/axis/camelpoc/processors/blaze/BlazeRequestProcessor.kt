package com.axis.camelpoc.processors.blaze

import com.axis.camelpoc.models.requests.BlazeDecisionCalculationRequest
import com.axis.camelpoc.models.requests.BlazeVariableCalculationRequest
import com.fasterxml.jackson.databind.ObjectMapper
import org.apache.camel.Exchange
import org.apache.camel.Processor

class BlazeRequestProcessor(private val objectMapper: ObjectMapper, private val type: String) : Processor {
    override fun process(exchange: Exchange?) {
        if(type == "variable"){
            val requestObj: BlazeVariableCalculationRequest = BlazeVariableCalculationRequest("100CTD", "variable")
            val request: String = objectMapper.writeValueAsString(requestObj)
            exchange?.getIn()?.body = request;
        }
        else if (type== "decision"){
            val requestObj: BlazeDecisionCalculationRequest = BlazeDecisionCalculationRequest("100CTD", "variable", 20, 45)
            val request: String = objectMapper.writeValueAsString(requestObj)
            exchange?.getIn()?.body = request;
        }
    }

}
