package com.axis.camelpoc.routers

import org.apache.camel.Processor
import com.axis.camelpoc.models.workflow.Endpoints
import com.axis.camelpoc.models.workflow.Workflow
import com.axis.camelpoc.processors.RequestProcessor
import com.axis.camelpoc.processors.ResponseProcessor
import org.apache.camel.builder.RouteBuilder

abstract class Router : RouteBuilder() {

    open var sourceUrl: String? = null;
    open var sourceName: String? = null;

    open var endpoints: MutableList<Endpoints> = mutableListOf()

    open var sourceWorkflow: MutableList<Workflow> = mutableListOf()

    class WorkFlowPriorityComparator {

        companion object : Comparator<Workflow> {
            override fun compare(a: Workflow, b: Workflow): Int =
                    a.getPriority() - b.getPriority()
        }
    }




}