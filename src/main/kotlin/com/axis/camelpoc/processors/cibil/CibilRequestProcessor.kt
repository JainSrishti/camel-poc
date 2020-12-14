package com.axis.camelpoc.processors.cibil

import org.slf4j.Logger
import org.slf4j.LoggerFactory
import com.axis.camelpoc.models.User
import org.apache.camel.Exchange
import com.axis.camelpoc.models.requests.CibilRequest
import com.axis.camelpoc.processors.RequestProcessor
import org.apache.camel.component.netty.http.NettyHttpMessage

class CibilRequestProcessor : RequestProcessor() {

    var log: Logger = LoggerFactory.getLogger(CibilRequestProcessor::class.java)

    override fun process(exchange: Exchange?) {

        val message = exchange?.getIn(NettyHttpMessage::class.java)
        val userJson: String? = message?.getBody(String::class.java)
        val user: User = objectMapper.readValue(userJson, User::class.java)

        log.info("User in Cibil Request processor: $user")

        if (sourceName == "MLP-PL") {
            val requestObj = CibilRequest(user)
            val request: String = objectMapper.writeValueAsString(requestObj)

            exchange?.getIn()?.setBody(request, String::class.java)
        }
    }
}