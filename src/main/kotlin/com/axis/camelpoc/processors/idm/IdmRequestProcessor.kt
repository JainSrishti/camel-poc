package com.axis.camelpoc.processors.idm

import com.axis.camelpoc.models.requests.IDMRequest
import com.fasterxml.jackson.databind.ObjectMapper
import org.apache.camel.Exchange
import org.apache.camel.Processor
import org.apache.camel.component.netty.http.NettyHttpMessage
import org.slf4j.Logger
import org.slf4j.LoggerFactory

class IdmRequestProcessor(private val objectMapper: ObjectMapper) : Processor {

    var log: Logger = LoggerFactory.getLogger(IdmRequestProcessor::class.java)

    override fun process(exchange: Exchange?) {
        val message = exchange?.getIn(NettyHttpMessage::class.java)
        val str: String? = message?.getBody(String::class.java)

        log.info("User in IDM Request processor: $str")

        val requestObj: IDMRequest = IDMRequest("100CTD")
        val request: String = objectMapper.writeValueAsString(requestObj)
        //val finalRequest:String = "[$request]"
        exchange?.getIn()?.setBody(request, String::class.java)
    }

}
