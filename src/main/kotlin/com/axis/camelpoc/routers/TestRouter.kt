package com.axis.camelpoc.routers

import com.axis.camelpoc.processors.RequestProcessor
import com.axis.camelpoc.processors.ResponseProcessor
import com.fasterxml.jackson.databind.ObjectMapper
import org.apache.camel.CamelExchangeException
import org.apache.camel.Exchange
import org.apache.camel.Processor
import org.apache.camel.ValidationException
import org.apache.camel.component.netty.http.NettyHttpOperationFailedException

class TestRouter: Router() {
    override fun configure() {
        var objectMapper: ObjectMapper = ObjectMapper()

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
                .pipeline("direct:cibilTest")
    }
}