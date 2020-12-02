package com.axis.camelpoc.startup

import com.axis.camelpoc.models.workflow.Endpoints
import com.axis.camelpoc.models.workflow.SourceSystem
import com.axis.camelpoc.routers.Router
import org.apache.camel.CamelContext
import org.apache.camel.impl.DefaultCamelContext
import org.springframework.boot.context.event.ApplicationReadyEvent
import org.springframework.context.ApplicationListener
import org.springframework.stereotype.Component

@Component
class StartupApplicationListener : ApplicationListener<ApplicationReadyEvent> {

    override fun onApplicationEvent(event: ApplicationReadyEvent) {

        // Endpoints for MLP ( To be loaded from DB later for every source system)
        var endpoints = mutableListOf<Endpoints>(
                Endpoints("1",
                        "cibil",
                        "http://localhost:9001/cibilPost",
                        "application/json",
                        "netty-http",
                        "POST",
                        "CibilRequestProcessor",
                        "",
                        "",
                        5000,
                        1,
                        true
                ),
                Endpoints("2",
                        "blazedecision",
                        "http://localhost:9002/blazeDecisionCalculate",
                        "application/json",
                        "netty-http",
                        "POST",
                        "BlazeDecisionCalculationRequestProcessor",
                        "",
                        "",
                        5000,
                        4,
                        true
                ),
                Endpoints("3",
                        "blazevariable",
                        "http://localhost:9002/blazeVariableCalculate",
                        "application/json",
                        "netty-http",
                        "POST",
                        "BlazeVariableCalculationRequestProcessor",
                        "",
                        "",
                        5000,
                        2,
                        false
                ),
                Endpoints("4",
                        "idm",
                        "http://localhost:9000/idmPost",
                        "application/json",
                        "netty-http",
                        "POST",
                        "IdmRequestProcessor",
                        "",
                        "MLPPLIDMException",
                        5000,
                        2,
                        true
                ),
                Endpoints("5",
                        "liability",
                        "http://localhost:9003/liabilityPost",
                        "application/json",
                        "netty-http",
                        "POST",
                        "LiabilityDbRequestProcessor",
                        "",
                        "",
                        5000,
                        3,
                        true
                )
        )

        var camelContext: CamelContext = DefaultCamelContext()
        // Set parameters in source
        var mlpSource: SourceSystem = SourceSystem("1",
                "MLP",
                1,
                "MLPPLRouter",
                "http://localhost:8090/process/user",
                endpoints
        )
        // Start for loop for all sources
        var router = Class.forName("com.axis.camelpoc.routers." + mlpSource.getSourceSystemRouter())

        var routerInstance = router.getDeclaredConstructor().newInstance() as Router

        routerInstance.source = mlpSource.getSourceUrl()
        routerInstance.endpoints = mlpSource.getEndpoints()
        camelContext.addRoutes(routerInstance)

        //End of FOR LOOP for all sources
        camelContext.start()
    }
}