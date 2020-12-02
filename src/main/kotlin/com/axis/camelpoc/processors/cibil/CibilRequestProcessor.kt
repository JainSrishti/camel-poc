package com.axis.camelpoc.processors.cibil

import com.axis.camelpoc.models.User
import org.apache.camel.Exchange
import org.apache.camel.Processor
import com.axis.camelpoc.models.requests.CibilRequest
import com.fasterxml.jackson.databind.ObjectMapper
import org.apache.camel.component.netty.http.NettyHttpMessage
import org.slf4j.Logger
import org.slf4j.LoggerFactory

class CibilRequestProcessor(private val objectMapper: ObjectMapper): Processor {

    var log: Logger = LoggerFactory.getLogger(CibilRequestProcessor::class.java)
    
    override fun process(exchange: Exchange?) {

        val message = exchange?.getIn(NettyHttpMessage::class.java)
        val userJson: String? = message?.getBody(String::class.java)
        val user: User = objectMapper.readValue(userJson, User::class.java)

        val requestObj = CibilRequest(user)
        val request: String = objectMapper.writeValueAsString(requestObj)
        log.info("User in MLP-PL Cibil Request processor: $request")

        exchange?.getIn()?.setBody(request, String::class.java)
    }
}