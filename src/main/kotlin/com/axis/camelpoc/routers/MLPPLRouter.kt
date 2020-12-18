package com.axis.camelpoc.routers

import org.apache.camel.Exchange
import org.apache.camel.CamelExchangeException
import org.apache.camel.ValidationException
import com.axis.camelpoc.utilities.DynamicRouteBean
import org.apache.camel.Processor
import org.apache.camel.builder.Builder.bean
import org.apache.camel.component.netty.http.NettyHttpOperationFailedException
import com.axis.camelpoc.processors.RequestProcessor.Companion.getRequestProcessor
import com.axis.camelpoc.processors.ResponseProcessor.Companion.getResponseProcessor

class MLPPLRouter : Router() {

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

        sourceWorkflow.sortWith(WorkFlowPriorityComparator)

        val startEndpoint = sourceWorkflow[0].getStart();
        val workflowMap: MutableMap<String, Pair<String, String?>> = mutableMapOf()

        for (workflow in sourceWorkflow) {
            workflowMap[workflow.getStart()] = Pair(workflow.getEnd(), workflow.getFailoverEndpoint())
        }

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
                        .process(endpoint.getResponseProcessor()?.let {
                            getResponseProcessor(it,
                                    endpoint.getEndpointName(),
                                    endpoint.getEndpointRouteName(),
                                    sourceName)
                        })
            }
        }

        // Workflow

        from("netty-http:$sourceUrl").process(Processor() {
            it.setProperty("Workflow", workflowMap)
        })
                .dynamicRouter(bean(DynamicRouteBean::class.java, "route(*, *, ${startEndpoint})"))
    }

    private fun processException(exceptionClassName: String): Throwable {
        return if (exceptionClassName != "") {
            val exceptionClass = Class.forName("com.axis.camelpoc.exceptions.$exceptionClassName")

            exceptionClass.getDeclaredConstructor().newInstance() as Throwable
        } else
            Throwable()
    }
}
