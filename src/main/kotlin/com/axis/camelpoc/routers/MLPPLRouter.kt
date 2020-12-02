package com.axis.camelpoc.routers

import com.axis.camelpoc.enums.Headers
import com.axis.camelpoc.exceptions.MLPPLIDMException
import com.axis.camelpoc.models.User
import com.axis.camelpoc.models.workflow.Endpoints
import com.fasterxml.jackson.databind.ObjectMapper
import org.apache.camel.CamelExchangeException
import org.apache.camel.Exchange
import org.apache.camel.Processor
import org.apache.camel.ValidationException
import org.apache.camel.component.netty.http.NettyHttpMessage
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
        var endpointNames = mutableListOf<String>()

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

      /* onException(MLPPLIDMException::class.java)
                .useOriginalMessage()
                .handled(true)
                .to("direct:blazevariable")*/

        for (endpoint in endpoints.iterator()) {
            if (endpoint.isEndpointMandatory()) {
                endpointNames.add("direct:" + endpoint.getEndpointRouteName())
            }

            from("direct:" + endpoint.getEndpointRouteName())
                    //.doTry()
                    .process(getRequestProcessor(endpoint.getEndpointProcessor(), endpoint.getEndpointRouteName(), objectMapper))
                    .to("log:DEBUG?showBody=true&showHeaders=true")
                    .setHeader(Exchange.HTTP_METHOD, simple(endpoint.getEndpointRequestMethod()))
                    .setHeader(Exchange.CONTENT_TYPE, constant(endpoint.getEndpointContentType()))
                    .to(endpoint.getEndpointRequestType() + ":" + endpoint.getEndpointUrl())
                   /* .doCatch(NettyHttpOperationFailedException::class.java)
                    .process(Processor() {
                        it.getIn().setHeader(Headers.ROUTE_ID.value, "idm")
                        val route: String? = it.getIn().getHeader(Headers.ROUTE_ID.value, String::class.java)
                        if (route != null && route == "idm") {
                            processException(endpoint.getEndpointException())
                        }
                    })*/
        }

        from("netty-http:$source")/*.process { exchange ->
            val message = exchange.getIn(NettyHttpMessage::class.java)
            val str: String = message.getBody(String::class.java)
            val user: User = objectMapper.readValue(str, User::class.java)
            log.info("User in main processor: $user")
        }*/
                .pipeline(*endpointNames.toTypedArray())
    }

    private fun getRequestProcessor(processorClassName: String, name: String, objectMapper: ObjectMapper): Processor {
        var processorClass = Class.forName("com.axis.camelpoc.processors.$name.$processorClassName")

        return processorClass.getDeclaredConstructor(ObjectMapper::class.java).newInstance(objectMapper) as Processor
    }

    private fun processException(exceptionClassName: String): Throwable {
        return if (exceptionClassName != "") {
            val exceptionClass = Class.forName("com.axis.camelpoc.exceptions.$exceptionClassName")

            exceptionClass.getDeclaredConstructor().newInstance() as Throwable
        } else
            Throwable()
    }
}
