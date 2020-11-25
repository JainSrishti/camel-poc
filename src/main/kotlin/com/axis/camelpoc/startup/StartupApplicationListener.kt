package com.axis.camelpoc.startup

import com.axis.camelpoc.models.workflow.ExternalIntegratorAdapters
import com.axis.camelpoc.models.workflow.SourceSystem
import com.axis.camelpoc.routes.MLPPLRouter
import org.apache.camel.CamelContext
import org.apache.camel.impl.DefaultCamelContext
import org.springframework.boot.context.event.ApplicationReadyEvent
import org.springframework.context.ApplicationListener
import org.springframework.stereotype.Component

@Component
class StartupApplicationListener : ApplicationListener<ApplicationReadyEvent> {


    override fun onApplicationEvent(event: ApplicationReadyEvent) {

        // Endpoints for MLP ( To be loaded from DB later for every source system)
        var endpoints = mutableListOf<ExternalIntegratorAdapters>(ExternalIntegratorAdapters("1",
                "cibil",
                "http://localhost:9001/cibilPost",
                "CibilRequestProcessor",
                "application/json",
                "netty-http",
                "POST",
                1,
                5000,
                5000
        ),
                ExternalIntegratorAdapters("2",
                        "idm",
                        "http://localhost:9000/idmPost",
                        "IdmRequestProcessor",
                        "application/json",
                        "netty-http",
                        "POST",
                        2,
                        5000,
                        5000
                ),
                ExternalIntegratorAdapters("3",
                        "blazeVariableCalculate",
                        "http://localhost:9002/blazeVariableCalculate",
                        "BlazeRequestProcessor",
                        "application/json",
                        "netty-http",
                        "POST",
                        3,
                        5000,
                        5000
                ),
                ExternalIntegratorAdapters("4",
                        "liability",
                        "http://localhost:9003/liabilityPost",
                        "LiabilityDbRequestProcessor",
                        "application/json",
                        "netty-http",
                        "POST",
                        4,
                        5000,
                        5000
                ),
                ExternalIntegratorAdapters("5",
                        "blazeDecisionCalculate",
                        "http://localhost:9002/blazeDecisionCalculate",
                        "BlazeRequestProcessor",
                        "application/json",
                        "netty-http",
                        "POST",
                        5,
                        5000,
                        5000
                )
        )

        var camelContext: CamelContext = DefaultCamelContext()
        // Set parameters in source
        var mlpSource: SourceSystem = SourceSystem("1",
        "MLP",
                "xyz",
                "MLPPLRouter",
                "netty-http",
                "POST",
                5000,
                5000,
                endpoints
        )
        // Start for loop for all sources
        var router = Class.forName("com.axis.camelpoc.routes." + mlpSource.getSourceSystemRouter())
        //
        var routerInstance = router.getDeclaredConstructor().newInstance()
        if(routerInstance is MLPPLRouter){
            routerInstance.setEndpoints(mlpSource.getEndpoints())
            camelContext.addRoutes(routerInstance)
        }

        //End of FOR LOOP for all sources
        camelContext.start()
    }
}