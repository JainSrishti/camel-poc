package com.axis.camelpoc.routes

import org.apache.camel.Exchange
import com.axis.camelpoc.models.User
import org.apache.camel.builder.RouteBuilder
import com.axis.camelpoc.processors.blaze.BlazeRequestProcessor
import com.axis.camelpoc.processors.blaze.BlazeResponseProcessor
import com.axis.camelpoc.processors.cibil.CibilRequestProcessor
import com.axis.camelpoc.processors.cibil.CibilResponseProcessor
import com.axis.camelpoc.processors.idm.IdmRequestProcessor
import com.axis.camelpoc.processors.idm.IdmResponseProcessor
import com.axis.camelpoc.processors.liability.LiabilityDbRequestProcessor
import com.axis.camelpoc.processors.liability.LiabilityDbResponseProcessor
import com.fasterxml.jackson.databind.ObjectMapper
import org.apache.camel.component.netty.http.NettyHttpMessage

class Router : RouteBuilder() {
    var objectMapper: ObjectMapper? = null

    override fun configure() {

        from("direct:start")
                .to("netty-http:http://localhost:8090/process/user")
                .process { exchange ->
                    val message = exchange.getIn(NettyHttpMessage::class.java)
                    val user: User = ObjectMapper().readValue(message.getBody(String::class.java), User::class.java)
                    log.info("User in main processor: $user")
                }.to("direct:cibilNetty")

        from("direct:cibilNetty").process(objectMapper?.let { CibilRequestProcessor(it) })
                .setHeader(Exchange.HTTP_METHOD, simple("POST"))
                .setHeader(Exchange.CONTENT_TYPE, constant("application/xml"))
                .to("netty-http:http://localhost:4601/cibilPost").process(CibilResponseProcessor())
                .to("direct:idmNetty")

        from("direct:idmNetty").process(objectMapper?.let { IdmRequestProcessor(it) })
                .setHeader(Exchange.HTTP_METHOD, simple("POST"))
                .setHeader(Exchange.CONTENT_TYPE, constant("application/xml"))
                .to("netty-http:http://localhost:4602/idmPost")
                /*.process(IdmResponseProcessor())
                .choice()
                .`when`(body().contains("Success"))*/
                .to("direct:blazeVariableCalculation")
        /*.otherwise()
        .to("direct:nsdlNetty")*/

        from("direct:blazeVariableCalculation").process(objectMapper?.let { BlazeRequestProcessor(it, "variable") })
                .setHeader(Exchange.HTTP_METHOD, simple("POST"))
                .setHeader(Exchange.CONTENT_TYPE, constant("application/xml"))
                .to("netty-http:http://localhost:4600/blazePost")
                .process(BlazeResponseProcessor())
                .to("direct:LiabilityDB")

        from("direct:nsdlNetty").process(objectMapper?.let { BlazeRequestProcessor(it, "none") })
                .setHeader(Exchange.HTTP_METHOD, simple("POST"))
                .setHeader(Exchange.CONTENT_TYPE, constant("application/xml"))
                .to("netty-http:http://localhost:4600/nsdlPost")
                .process(BlazeResponseProcessor())
                .to("direct:blazeVariableCalculation")

        from("direct:LiabilityDB").process(LiabilityDbRequestProcessor())
                .setHeader(Exchange.HTTP_METHOD, simple("POST"))
                .setHeader(Exchange.CONTENT_TYPE, constant("application/xml"))
                .to("netty-http:http://localhost:4603/liabilityPost")
                .process(LiabilityDbResponseProcessor())
                .to("direct:blazeDecisionCalculation")

        from("direct:blazeDecisionCalculation").process(objectMapper?.let { BlazeRequestProcessor(it, "decision") })
                .setHeader(Exchange.HTTP_METHOD, simple("POST"))
                .setHeader(Exchange.CONTENT_TYPE, constant("application/xml"))
                .to("netty-http:http://localhost:4600/blazePost")
                .process(BlazeResponseProcessor())

    }
}