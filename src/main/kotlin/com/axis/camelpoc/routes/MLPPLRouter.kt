package com.axis.camelpoc.routes

import com.axis.camelpoc.models.User
import com.axis.camelpoc.models.workflow.ExternalIntegratorAdapters
import com.fasterxml.jackson.databind.ObjectMapper
import org.apache.camel.CamelExchangeException
import org.apache.camel.Exchange
import org.apache.camel.Processor
import org.apache.camel.ValidationException
import org.apache.camel.builder.RouteBuilder
import org.apache.camel.component.netty.http.NettyHttpMessage
import org.apache.camel.component.netty.http.NettyHttpOperationFailedException

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

        var objectMapper: ObjectMapper = ObjectMapper()
        endpoints.sortWith(ExternalIntegratorAdaptersComparator)

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

        from("netty-http:http://localhost:8090/process/user").process { exchange ->
            val message = exchange.getIn(NettyHttpMessage::class.java)
            val str: String = message.getBody(String::class.java)
            val user: User = objectMapper.readValue(str, User::class.java)
            log.info("User in main processor: $user")
        }
                .pipeline("direct:cibil", "direct:idm", "direct:blazevariable", "direct:liability", "direct:blazedecision")


        for (i in endpoints.iterator()) {
            from("direct:" + i.getName()).process(getProcessor(i.getIntegratorEndpointProcessor(), i.getName(), objectMapper))
                    .to("log:DEBUG?showBody=true&showHeaders=true")
                    .setHeader(Exchange.HTTP_METHOD, simple(i.getIntegratorEndpointConnectionMethod()))
                    .setHeader(Exchange.CONTENT_TYPE, constant(i.getIntegratorEndpointContentType()))
                    .to(i.getIntegratorEndpointConnectionType() + ":" + i.getIntegratorEndpointConnectionString())
        }
    }

    private fun getProcessor(processorClassName: String, name: String, objectMapper: ObjectMapper): Processor {
        var processorClass = Class.forName("com.axis.camelpoc.processors.$name.$processorClassName")

        return processorClass.getDeclaredConstructor(ObjectMapper::class.java).newInstance(objectMapper) as Processor
    }

}