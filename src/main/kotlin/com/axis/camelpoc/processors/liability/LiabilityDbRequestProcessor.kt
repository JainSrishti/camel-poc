package com.axis.camelpoc.processors.liability


import org.slf4j.Logger
import org.slf4j.LoggerFactory
import com.axis.camelpoc.processors.RequestProcessor
import org.apache.camel.Exchange
import org.apache.camel.component.netty.http.NettyHttpMessage

class LiabilityDbRequestProcessor() : RequestProcessor() {

    var log: Logger = LoggerFactory.getLogger(LiabilityDbRequestProcessor::class.java)

    override fun process(exchange: Exchange?) {
        val message = exchange?.getIn(NettyHttpMessage::class.java)
        val str: String? = message?.getBody(String::class.java)

        log.info("User in Liability Request processor: $str")

        if (sourceName == "MLP-PL") {
            val finalRequest: String = "{\"userId\" : \"CODE12309\",\n" +
                    "\"sourceId\" : 10\n" +
                    "}"
            exchange?.getIn()?.setBody(finalRequest, String::class.java)
        }
    }

}
