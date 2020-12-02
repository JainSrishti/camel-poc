package com.axis.camelpoc.routers

import com.axis.camelpoc.models.workflow.Endpoints
import org.apache.camel.builder.RouteBuilder

abstract class Router : RouteBuilder(){

    open var source: String? = null;
    open var endpoints: MutableList<Endpoints> = mutableListOf()
}