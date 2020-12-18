package com.axis.camelpoc.startup

import org.apache.camel.CamelContext
import com.axis.camelpoc.routers.Router
import org.apache.camel.impl.DefaultCamelContext
import org.springframework.boot.context.event.ApplicationReadyEvent
import org.springframework.context.ApplicationListener
import org.springframework.stereotype.Component
import com.axis.camelpoc.models.workflow.Endpoints
import com.axis.camelpoc.models.workflow.SourceSystem
import com.axis.camelpoc.models.workflow.Workflow

@Component
class StartupApplicationListener : ApplicationListener<ApplicationReadyEvent> {

    override fun onApplicationEvent(event: ApplicationReadyEvent) {

        // Endpoints for MLP ( To be loaded from DB later for every source system)
        var mlpPlEndpoints = mutableListOf<Endpoints>(
                Endpoints("1",
                        "cibil",
                        "mlp_pl_cibil",
                        "endpoint",
                        "http://localhost:9001/cibilPost",
                        "application/json",
                        "netty-http",
                        "POST",
                        "CibilRequestProcessor",
                        "CibilResponseProcessor",
                        "",
                        "",
                        0,
                        5000
                ),
                Endpoints("2",
                        "blaze",
                        "mlp_pl_blaze_decision",
                        "endpoint",
                        "http://localhost:9002/blazeDecisionCalculate",
                        "application/json",
                        "netty-http",
                        "POST",
                        "BlazeDecisionCalculationRequestProcessor",
                        "BlazeDecisionCalculationResponseProcessor",
                        "",
                        "",
                        0,
                        5000
                ),
                Endpoints("3",
                        "blaze",
                        "mlp_pl_blaze_variable",
                        "endpoint",
                        "http://localhost:9002/blazeVariableCalculate",
                        "application/json",
                        "netty-http",
                        "POST",
                        "BlazeVariableCalculationRequestProcessor",
                        "BlazeVariableCalculationResponseProcessor",
                        "",
                        "",
                        0,
                        5000
                ),
                Endpoints("4",
                        "idm",
                        "mlp_pl_idm",
                        "endpoint",
                        "http://localhost:9000/idmPost",
                        "application/json",
                        "netty-http",
                        "POST",
                        "IdmRequestProcessor",
                        "IdmResponseProcessor",
                        "",
                        "MLPPLIDMException",
                        0,
                        5000
                ),
                Endpoints("5",
                        "liability",
                        "mlp_pl_liability",
                        "endpoint",
                        "http://localhost:9003/liabilityPost",
                        "application/json",
                        "netty-http",
                        "POST",
                        "LiabilityDbRequestProcessor",
                        "LiabilityDbResponseProcessor",
                        "",
                        "",
                        0,
                        5000
                ),
                Endpoints("6",
                        "source",
                        "mlp_pl_source",
                        "source",
                        "http://localhost:8090/process/user",
                        "application/json",
                        "netty-http",
                        "POST",
                        null,
                        null,
                        null,
                        null,
                        0,
                        5000
                )
        )
        var mlpPlWorkflow: MutableList<Workflow> = mutableListOf(
                Workflow(1,
                        "direct://mlp_pl_cibil",
                        "direct://mlp_pl_idm",
                        null,
                        1),
                Workflow(2,
                        "direct://mlp_pl_idm",
                        "direct://mlp_pl_liability",
                        "direct://mlp_pl_blaze_variable",
                        2),
                Workflow(3,
                        "direct://mlp_pl_blaze_variable",
                        "direct://mlp_pl_liability",
                        null,
                        2),
                Workflow(1,
                        "direct://mlp_pl_liability",
                        "direct://mlp_pl_blaze_decision",
                        null,
                        3)
        )

        var camelContext: CamelContext = DefaultCamelContext()
        // Set parameters in source
        var mlpSource: SourceSystem = SourceSystem(1,
                "MLP-PL",
                1,
                "MLPPLRouter",
                mlpPlEndpoints,
                mlpPlWorkflow
        )

        var testSourceEndpoints = mutableListOf<Endpoints>(
                Endpoints("1",
                        "source",
                        "test_source_source",
                        "source",
                        "http://localhost:8050/process/user",
                        "application/json",
                        "netty-http",
                        "POST",
                        null,
                        null,
                        null,
                        null,
                        0,
                        5000
                ),
                Endpoints("2",
                        "cibil",
                        "test_source_cibil",
                        "endpoint",
                        "http://localhost:9001/cibilPost",
                        "application/json",
                        "netty-http",
                        "POST",
                        "CibilRequestProcessor",
                        "",
                        "",
                        "",
                        10,
                        5000
                ))

        var testSource: SourceSystem = SourceSystem(2,
                "Test",
                2,
                "TestRouter",
                testSourceEndpoints,
                mlpPlWorkflow
        )

        var sources: List<SourceSystem> = mutableListOf(mlpSource, testSource)

        // Start for loop for all sources
        for (source in sources) {
            var router = Class.forName("com.axis.camelpoc.routers." + source.getSourceSystemRouter())

            var routerInstance = router.getDeclaredConstructor().newInstance() as Router

            routerInstance.sourceName = source.getSourceSystemName()
            routerInstance.endpoints = source.getEndpoints()
            routerInstance.sourceWorkflow = source.getWorkflow()

            camelContext.addRoutes(routerInstance)
        }

        //End of FOR LOOP for all sources
        camelContext.start()
    }
}