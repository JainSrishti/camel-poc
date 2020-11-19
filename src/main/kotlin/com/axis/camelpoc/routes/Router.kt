package com.axis.camelpoc.routes

import com.axis.camelpoc.models.User
import com.axis.camelpoc.processors.blaze.BlazeRequestProcessor
import com.axis.camelpoc.processors.cibil.CibilRequestProcessor
import com.axis.camelpoc.processors.idm.IdmRequestProcessor
import com.axis.camelpoc.processors.liability.LiabilityDbRequestProcessor
import com.fasterxml.jackson.databind.ObjectMapper
import org.apache.camel.Exchange
import org.apache.camel.builder.RouteBuilder
import org.apache.camel.component.netty.http.NettyHttpMessage

class Router : RouteBuilder() {

    override fun configure() {

        var objectMapper: ObjectMapper = ObjectMapper()
        from("netty-http:http://localhost:8090/process/user")
                .process { exchange ->
                    val message = exchange.getIn(NettyHttpMessage::class.java)
                    val str: String = message.getBody(String::class.java)
                    val user: User = objectMapper.readValue(str, User::class.java)
                    log.info("User in main processor: $user")
                }.to("direct:cibilNetty")

        from("direct:cibilNetty").to("log:DEBUG?showBody=true&showHeaders=true").process(objectMapper?.let { CibilRequestProcessor(it) })
                .setHeader(Exchange.HTTP_METHOD, simple("POST"))
                .setHeader(Exchange.CONTENT_TYPE, constant("application/json"))
                .to("netty-http:http://localhost:4601/cibilPost").to("log:DEBUG?showBody=true&showHeaders=true").convertBodyTo(String::class.java)
                .to("direct:idmNetty")

        from("direct:idmNetty").to("log:DEBUG?showBody=true&showHeaders=true").process(objectMapper?.let { IdmRequestProcessor(it) })
                .setHeader(Exchange.HTTP_METHOD, simple("POST"))
                .setHeader(Exchange.CONTENT_TYPE, constant("application/json"))
                .to("netty-http:http://localhost:4602/idmPost").to("log:DEBUG?showBody=true&showHeaders=true").convertBodyTo(String::class.java)
                //.to("direct:blazeVariableCalculation")

        from("direct:blazeVariableCalculation").process(objectMapper?.let { BlazeRequestProcessor(it, "variable") })
                .setHeader(Exchange.HTTP_METHOD, simple("POST"))
                .setHeader(Exchange.CONTENT_TYPE, constant("application/json"))
                .to("netty-http:http://localhost:4600/blazePost")
                .to("direct:LiabilityDB")

        from("direct:nsdlNetty").process(objectMapper?.let { BlazeRequestProcessor(it, "none") })
                .setHeader(Exchange.HTTP_METHOD, simple("POST"))
                .setHeader(Exchange.CONTENT_TYPE, constant("application/json"))
                .to("netty-http:http://localhost:4600/nsdlPost")
                .to("direct:blazeVariableCalculation")

        from("direct:LiabilityDB").process(LiabilityDbRequestProcessor())
                .setHeader(Exchange.HTTP_METHOD, simple("POST"))
                .setHeader(Exchange.CONTENT_TYPE, constant("application/json"))
                .to("netty-http:http://localhost:4603/liabilityPost")
                .to("direct:blazeDecisionCalculation")

        from("direct:blazeDecisionCalculation").process(objectMapper?.let { BlazeRequestProcessor(it, "decision") })
                .setHeader(Exchange.HTTP_METHOD, simple("POST"))
                .setHeader(Exchange.CONTENT_TYPE, constant("application/json"))
                .to("netty-http:http://localhost:4600/blazePost")
                to("direct:result")
    }
}