package com.axis.camelpoc.routes

import com.axis.camelpoc.models.User
import com.axis.camelpoc.processors.blaze.BlazeRequestProcessor
import com.axis.camelpoc.processors.cibil.CibilRequestProcessor
import com.axis.camelpoc.processors.idm.IdmRequestProcessor
import com.axis.camelpoc.processors.liability.LiabilityDbRequestProcessor
import com.fasterxml.jackson.databind.ObjectMapper
import org.apache.camel.CamelExchangeException
import org.apache.camel.Exchange
import org.apache.camel.ValidationException
import org.apache.camel.builder.RouteBuilder
import org.apache.camel.component.netty.http.NettyHttpMessage
import org.apache.camel.component.netty.http.NettyHttpOperationFailedException

class Router : RouteBuilder() {

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
                .to("log:DEBUG?showBody=true&showHeaders=true")
                .handled(true)
                .transform(exceptionMessage())

        from("netty-http:http://localhost:8090/process/user").process { exchange ->
            val message = exchange.getIn(NettyHttpMessage::class.java)
            val str: String = message.getBody(String::class.java)
            val user: User = objectMapper.readValue(str, User::class.java)
            log.info("User in main processor: $user")
        }
                .pipeline("direct:cibilNetty", "direct:idmNetty", "direct:blazeVariableCalculation", "direct:LiabilityDB", "direct:blazeDecisionCalculation")

        from("direct:cibilNetty")
                .to("log:DEBUG?showBody=true&showHeaders=true")
                .process(objectMapper?.let { CibilRequestProcessor(it) })
                .setHeader(Exchange.HTTP_METHOD, simple("POST"))
                .setHeader(Exchange.CONTENT_TYPE, constant("application/json"))
                .to("netty-http:http://localhost:9001/cibilPost")

        from("direct:idmNetty")
                .to("log:DEBUG?showBody=true&showHeaders=true")
                .process(objectMapper?.let { IdmRequestProcessor(it) })
                .setHeader(Exchange.HTTP_METHOD, simple("POST"))
                .setHeader(Exchange.CONTENT_TYPE, constant("application/json"))
                .to("netty-http:http://localhost:9000/idmPost")

        from("direct:blazeVariableCalculation")
                .to("log:DEBUG?showBody=true&showHeaders=true")
                .process(objectMapper?.let { BlazeRequestProcessor(it, "variable") })
                .setHeader(Exchange.HTTP_METHOD, simple("POST"))
                .setHeader(Exchange.CONTENT_TYPE, constant("application/json"))
                .to("netty-http:http://localhost:9002/blazeVariableCalculate")


        from("direct:LiabilityDB").process(LiabilityDbRequestProcessor())
                .to("log:DEBUG?showBody=true&showHeaders=true")
                .setHeader(Exchange.HTTP_METHOD, simple("POST"))
                .setHeader(Exchange.CONTENT_TYPE, constant("application/json"))
                .to("netty-http:http://localhost:9003/liabilityPost")

        from("direct:blazeDecisionCalculation")
                .to("log:DEBUG?showBody=true&showHeaders=true")
                .process(objectMapper?.let { BlazeRequestProcessor(it, "decision") })
                .setHeader(Exchange.HTTP_METHOD, simple("POST"))
                .setHeader(Exchange.CONTENT_TYPE, constant("application/json"))
                .to("netty-http:http://localhost:9002/blazeDecisionCalculate")

    }
}