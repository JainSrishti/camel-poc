package com.axis.camelpoc.routes

import com.axis.camelpoc.models.workflow.ExternalIntegratorAdapters
import org.apache.camel.Exchange
import org.apache.camel.Processor
import org.apache.camel.builder.RouteBuilder

class MLPPLRouter() : RouteBuilder() {

    private var endpoints: MutableList<ExternalIntegratorAdapters> = mutableListOf()
    class ExternalIntegratorAdaptersComparator {

        companion object : Comparator<ExternalIntegratorAdapters> {

            override fun compare(a: ExternalIntegratorAdapters, b: ExternalIntegratorAdapters): Int =
                    a.getIntegratorEndpointPriority() - b.getIntegratorEndpointPriority()
        }
    }

    fun setEndpoints(endpoints: MutableList<ExternalIntegratorAdapters>) {
        this.endpoints = endpoints
    }

    override fun configure() {
        // Sort endpoints by priority
        endpoints.sortWith(ExternalIntegratorAdaptersComparator)

        for (i in endpoints.iterator()) {
            from("direct:" + i.getName()).process(getProcessor(i.getIntegratorEndpointProcessor(), i.getName()))
                    .setHeader(Exchange.HTTP_METHOD, simple(i.getIntegratorEndpointConnectionMethod()))
                    .setHeader(Exchange.CONTENT_TYPE, constant(i.getIntegratorEndpointContentType()))
                    .to(i.getIntegratorEndpointConnectionType() + ":" + i.getIntegratorEndpointConnectionString())
        }
    }

    private fun getProcessor(processorClassName: String, name: String): Processor {
        var processorClass = if(name.startsWith("blaze")) {
            Class.forName("com.axis.camelpoc.processors.$processorClassName")
        }
        else
            Class.forName("com.axis.camelpoc.processors.$name.$processorClassName")


        var type = processorClass.getDeclaredConstructor().newInstance() as Processor

        println("Processor")
        return type

    }

}