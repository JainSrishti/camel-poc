package com.axis.camelpoc.processors.blazedecision

import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.apache.camel.Exchange
import org.apache.camel.Processor
import com.fasterxml.jackson.databind.ObjectMapper
import org.apache.camel.component.netty.http.NettyHttpMessage
import com.axis.camelpoc.models.requests.BlazeDecisionCalculationRequest

class BlazeDecisionCalculationRequestProcessor(private val objectMapper: ObjectMapper) : Processor {
    override fun process(exchange: Exchange?) {

        var log: Logger = LoggerFactory.getLogger(BlazeDecisionCalculationRequestProcessor::class.java)

        val message = exchange?.getIn(NettyHttpMessage::class.java)
        val str: String? = message?.getBody(String::class.java)

        log.info("User in Blaze Decision Request processor: $str")

        val requestObj = BlazeDecisionCalculationRequest("100CTD", "variable", 20, 45)
        val request: String = objectMapper.writeValueAsString(requestObj)
        exchange?.getIn()?.setBody(request, String::class.java)
    }
}
