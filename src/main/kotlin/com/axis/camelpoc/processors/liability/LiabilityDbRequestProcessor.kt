package com.axis.camelpoc.processors.liability

import org.apache.camel.Exchange
import org.apache.camel.Processor

class LiabilityDbRequestProcessor: Processor {
    override fun process(exchange: Exchange?) {
        val finalRequest: String = "[{}]"
        exchange?.getIn()?.setBody(finalRequest, String::class.java)
    }

}
