package com.axis.camelpoc.routers

import com.axis.camelpoc.processors.RequestProcessor.Companion.getRequestProcessor
import org.apache.camel.Exchange
import org.apache.camel.ValidationException
import org.apache.camel.CamelExchangeException
import org.apache.camel.component.netty.http.NettyHttpOperationFailedException

class TestRouter : Router() {

    override fun configure() {

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

            if (endpoint.getRouteType().equals("source",
                            true)) {
                sourceUrl = endpoint.getUrl()
            } else {
                from("direct:" + endpoint.getEndpointRouteName())
                        .process(endpoint.getRequestProcessor()?.let {
                            getRequestProcessor(it,
                                    endpoint.getEndpointName(),
                                    sourceName)
                        })
                        .to("log:DEBUG?showBody=true&showHeaders=true")
                        .setHeader(Exchange.HTTP_METHOD, simple(endpoint.getRequestMethod()))
                        .setHeader(Exchange.CONTENT_TYPE, constant(endpoint.getEndpointContentType()))
                        .to(endpoint.getRequestType() + ":" + endpoint.getUrl())
            }
        }

        from("netty-http:$sourceUrl")
                .pipeline("direct:cibilTest")
    }
}