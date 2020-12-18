package com.axis.camelpoc.processors

import org.apache.camel.Processor
import com.fasterxml.jackson.databind.ObjectMapper

abstract class ResponseProcessor : Processor {

    open var sourceName: String? = null
    open var routeName: String? = null

    companion object{
        val objectMapper: ObjectMapper = ObjectMapper()

        fun getResponseProcessor(processorClassName: String, name: String, routeName: String, sourceName: String?): Processor {
            var processorClass = Class.forName("com.axis.camelpoc.processors.$name.$processorClassName")

            var responseProcessor = processorClass.getDeclaredConstructor().newInstance() as ResponseProcessor
            responseProcessor.sourceName = sourceName
            responseProcessor.routeName = routeName
            return responseProcessor
        }
    }
}