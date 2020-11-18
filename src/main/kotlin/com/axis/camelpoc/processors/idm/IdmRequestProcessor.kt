package com.axis.camelpoc.processors.idm

import com.axis.camelpoc.models.requests.IDMRequest
import com.fasterxml.jackson.databind.ObjectMapper
import org.apache.camel.Exchange
import org.apache.camel.Processor

class IdmRequestProcessor(val objectMapper: ObjectMapper) : Processor {
    override fun process(exchange: Exchange?) {
        val requestObj: IDMRequest = IDMRequest("100CTD")

        val request: String = objectMapper.writeValueAsString(requestObj)

        exchange?.getIn()?.body = request
    }

}
