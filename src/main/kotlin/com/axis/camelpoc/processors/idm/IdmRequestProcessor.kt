package com.axis.camelpoc.processors.idm

import org.slf4j.Logger
import org.slf4j.LoggerFactory
import com.axis.camelpoc.models.requests.IDMRequest
import com.axis.camelpoc.processors.RequestProcessor
import org.apache.camel.Exchange
import org.apache.camel.component.netty.http.NettyHttpMessage

class IdmRequestProcessor() : RequestProcessor() {

    var log: Logger = LoggerFactory.getLogger(IdmRequestProcessor::class.java)

    override fun process(exchange: Exchange?) {
        val message = exchange?.getIn(NettyHttpMessage::class.java)
        val str: String? = message?.getBody(String::class.java)

        log.info("User in IDM Request processor: $str")

        if(sourceName == "MLP-PL"){
            val requestObj = IDMRequest("100CTD")
            val request = objectMapper.writeValueAsString(requestObj)
            exchange?.getIn()?.setBody(request, String::class.java)
        }
    }

}
