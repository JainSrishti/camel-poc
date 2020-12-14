package com.axis.camelpoc.processors.blazevariable

import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.apache.camel.Exchange
import com.axis.camelpoc.models.requests.BlazeVariableCalculationRequest
import com.axis.camelpoc.processors.RequestProcessor
import org.apache.camel.component.netty.http.NettyHttpMessage

class BlazeVariableCalculationRequestProcessor : RequestProcessor() {
    override fun process(exchange: Exchange?) {

        var log: Logger = LoggerFactory.getLogger(BlazeVariableCalculationRequestProcessor::class.java)

        val message = exchange?.getIn(NettyHttpMessage::class.java)
        val str: String? = message?.getBody(String::class.java)

        log.info("User in Blaze Variable Request processor: $str")

        if(sourceName == "MLP-PL"){
            val requestObj = BlazeVariableCalculationRequest("100CTD", "variable")
            val request: String? = objectMapper?.writeValueAsString(requestObj)
            exchange?.getIn()?.setBody(request, String::class.java)
        }
    }
}
