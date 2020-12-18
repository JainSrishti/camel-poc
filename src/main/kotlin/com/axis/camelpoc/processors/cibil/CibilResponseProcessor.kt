package com.axis.camelpoc.processors.cibil

import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.apache.camel.Exchange
import org.apache.camel.component.netty.http.NettyHttpMessage
import com.axis.camelpoc.processors.ResponseProcessor

class CibilResponseProcessor: ResponseProcessor() {

    var log: Logger = LoggerFactory.getLogger(CibilResponseProcessor::class.java)

    override fun process(exchange: Exchange?) {
        val message = exchange?.getIn(NettyHttpMessage::class.java)
        val payload: String? = message?.getBody(String::class.java)

        log.info("User in Cibil Response processor: $payload")

        exchange?.getIn()?.setHeader(routeName, true)
    }

}
