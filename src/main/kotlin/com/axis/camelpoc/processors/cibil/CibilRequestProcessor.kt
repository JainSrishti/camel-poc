package com.axis.camelpoc.processors.cibil

import org.apache.camel.Exchange
import org.apache.camel.Processor
import com.axis.camelpoc.models.requests.CibilRequest
import com.fasterxml.jackson.databind.ObjectMapper

class CibilRequestProcessor(private val objectMapper: ObjectMapper): Processor {


    override fun process(exchange: Exchange?) {
        val request: String = objectMapper.writeValueAsString(exchange?.getIn()?.getBody(CibilRequest::class.java))

        exchange?.getIn()?.setBody(request, String::class.java)
    }
}