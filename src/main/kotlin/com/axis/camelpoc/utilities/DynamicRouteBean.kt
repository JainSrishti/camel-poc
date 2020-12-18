package com.axis.camelpoc.utilities

import org.apache.camel.Exchange
import org.apache.camel.Header
import org.apache.camel.component.netty.http.NettyHttpMessage

class DynamicRouteBean {

    fun route(@Header(Exchange.SLIP_ENDPOINT) previousRoute: String?, exchange: Exchange, startEndpoint: String): String? {

        val workflow: MutableMap<String, Pair<String, String?>> = exchange.getProperty("Workflow") as MutableMap<String, Pair<String, String?>>

        return when {
            previousRoute == null ->
                "$startEndpoint"

            exchange.getIn(NettyHttpMessage::class.java).getHeader(previousRoute.substring(9)) == true ->
                "${workflow[previousRoute]?.first}"

            exchange.getIn(NettyHttpMessage::class.java).getHeader(previousRoute.substring(9)) != true -> {
                if (workflow[previousRoute]?.second != null) {
                    "${workflow[previousRoute]?.second}"
                } else
                    null
            }
            else ->
                null
        }
    }
}