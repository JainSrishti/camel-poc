package com.axis.camelpoc.routers

import org.apache.camel.Exchange
import org.apache.camel.Processor
import org.apache.camel.CamelExchangeException
import org.apache.camel.ValidationException
import com.fasterxml.jackson.databind.ObjectMapper
import com.axis.camelpoc.enums.Headers
import com.axis.camelpoc.models.workflow.Endpoints
import com.axis.camelpoc.processors.RequestProcessor
import com.axis.camelpoc.processors.ResponseProcessor
import org.apache.camel.component.netty.http.NettyHttpOperationFailedException

class MLPPLRouter : Router() {

    class EndpointPriorityComparator {

        companion object : Comparator<Endpoints> {
            override fun compare(a: Endpoints, b: Endpoints): Int =
                    a.getEndpointPriority() - b.getEndpointPriority()
        }
    }

    override fun configure() {

        var objectMapper: ObjectMapper = ObjectMapper()
        var flow0 = mutableListOf<String>()
        var flow1 = mutableListOf<String>()
        var flow2 = mutableListOf<String>()

        endpoints.sortWith(EndpointPriorityComparator)

        onException(NettyHttpOperationFailedException::class.java)
                .useOriginalMessage()
                .maximumRedeliveries(3)
                .handled(true)
                .transform(exceptionMessage())

        onException(CamelExchangeException::class.java)
                .useOriginalMessage()
                .maximumRedeliveries(3)
                .handled(true)
                .transform(exceptionMessage())

        onException(ValidationException::class.java)
                .useOriginalMessage()
                .maximumRedeliveries(3)
                .handled(true)
                .transform(exceptionMessage())

        flow0.add("direct:cibil")
        flow0.add("direct:idm")

        flow2.add("direct:blazevariable")
        flow2.add("direct:liability")
        flow2.add("direct:blazedecision")

        flow1.add("direct:liability")
        flow1.add("direct:blazedecision")


        for (endpoint in endpoints.iterator()) {

            from("direct:" + endpoint.getEndpointRouteName())
                    .process(getRequestProcessor(endpoint.getEndpointProcessor(), endpoint.getEndpointRouteName(), sourceName))
                    .to("log:DEBUG?showBody=true&showHeaders=true")
                    .setHeader(Exchange.HTTP_METHOD, simple(endpoint.getEndpointRequestMethod()))
                    .setHeader(Exchange.CONTENT_TYPE, constant(endpoint.getEndpointContentType()))
                    .to(endpoint.getEndpointRequestType() + ":" + endpoint.getEndpointUrl())
                    .process()
        }

        from("netty-http:$sourceUrl")
                .pipeline(*flow0.toTypedArray()).choice()
                .`when`(header(Headers.IDM.value).contains(false))
                .pipeline(*flow2.toTypedArray())
                .otherwise()
                .pipeline(*flow1.toTypedArray())

    }

    private fun processException(exceptionClassName: String): Throwable {
        return if (exceptionClassName != "") {
            val exceptionClass = Class.forName("com.axis.camelpoc.exceptions.$exceptionClassName")

            exceptionClass.getDeclaredConstructor().newInstance() as Throwable
        } else
            Throwable()
    }
}
