package com.axis.camelpoc.processors

import com.axis.camelpoc.models.requests.BlazeDecisionCalculationRequest
import com.axis.camelpoc.models.requests.BlazeVariableCalculationRequest
import com.fasterxml.jackson.databind.ObjectMapper
import org.apache.camel.Exchange
import org.apache.camel.Processor
import org.apache.camel.component.netty.http.NettyHttpMessage
import org.slf4j.Logger
import org.slf4j.LoggerFactory

class BlazeRequestProcessor() : Processor {
    override fun process(exchange: Exchange?) {

        val objectMapper: ObjectMapper? = null

         val type: String? = null

        var log: Logger = LoggerFactory.getLogger(BlazeRequestProcessor::class.java)

        val message = exchange?.getIn(NettyHttpMessage::class.java)
        val str: String? = message?.getBody(String::class.java)

        log.info("User in Blaze Request processor: $str")

        if(type == "variable"){
            val requestObj: BlazeVariableCalculationRequest = BlazeVariableCalculationRequest("100CTD", "variable")
            val request: String? = objectMapper?.writeValueAsString(requestObj)
            exchange?.getIn()?.setBody(request, String::class.java)
        }
        else if (type== "decision"){
            val requestObj: BlazeDecisionCalculationRequest = BlazeDecisionCalculationRequest("100CTD", "variable", 20, 45)
            val request: String? = objectMapper?.writeValueAsString(requestObj)
            exchange?.getIn()?.setBody(request, String::class.java)
        }
    }

}
