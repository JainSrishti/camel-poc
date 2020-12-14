package com.axis.camelpoc.processors

import org.apache.camel.Processor
import com.fasterxml.jackson.databind.ObjectMapper

abstract class RequestProcessor() : Processor {

    open var sourceName: String? = null

    companion object{
        val objectMapper: ObjectMapper = ObjectMapper()
    }
}