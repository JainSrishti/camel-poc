package com.axis.camelpoc.processors.blaze

import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.apache.camel.Exchange
import com.axis.camelpoc.processors.ResponseProcessor
import org.apache.camel.component.netty.http.NettyHttpMessage

class BlazeDecisionCalculationResponseProcessor: ResponseProcessor() {

    var log: Logger = LoggerFactory.getLogger(BlazeDecisionCalculationResponseProcessor::class.java)

    override fun process(exchange: Exchange?) {
        val message = exchange?.getIn(NettyHttpMessage::class.java)
        val payload: String? = message?.getBody(String::class.java)

        log.info("User in BlazeDecisionCalculation Response processor: $payload")

        exchange?.getIn()?.setHeader(routeName, true)
    }
}