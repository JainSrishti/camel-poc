package com.axis.camelpoc.routers

import com.axis.camelpoc.models.workflow.Endpoints
import com.axis.camelpoc.processors.RequestProcessor
import com.axis.camelpoc.processors.ResponseProcessor
import org.apache.camel.Processor
import org.apache.camel.builder.RouteBuilder

abstract class Router : RouteBuilder() {

    open var sourceUrl: String? = null;
    open var sourceName: String? = null;
    open var endpoints: MutableList<Endpoints> = mutableListOf()

    fun getRequestProcessor(processorClassName: String, name: String, sourceName: String?): Processor {
        var processorClass = Class.forName("com.axis.camelpoc.processors.$name.$processorClassName")

        var requestProcessor = processorClass.getDeclaredConstructor().newInstance() as RequestProcessor
        requestProcessor.sourceName = sourceName
        return requestProcessor
    }
}