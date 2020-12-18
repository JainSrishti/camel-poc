package com.axis.camelpoc.processors

import org.apache.camel.Processor
import com.fasterxml.jackson.databind.ObjectMapper

abstract class RequestProcessor() : Processor {

    open var sourceName: String? = null

    companion object{
        val objectMapper: ObjectMapper = ObjectMapper()

        fun getRequestProcessor(processorClassName: String, name: String, sourceName: String?): Processor {
            var processorClass = Class.forName("com.axis.camelpoc.processors.$name.$processorClassName")

            var requestProcessor = processorClass.getDeclaredConstructor().newInstance() as RequestProcessor
            requestProcessor.sourceName = sourceName
            return requestProcessor
        }
    }
}