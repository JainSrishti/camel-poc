package com.axis.camelpoc.service

import com.axis.camelpoc.routes.Router
import com.axis.camelpoc.models.others.Endpoints
import com.axis.camelpoc.models.others.RequestResponse
import com.axis.camelpoc.models.others.SourceSystem
import com.axis.camelpoc.repo.EndpointRepo
import com.axis.camelpoc.repo.ReqResRepo
import com.axis.camelpoc.repo.SourceSystemRepo
import com.fasterxml.jackson.databind.ObjectMapper
import org.apache.camel.impl.DefaultCamelContext
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.context.event.ApplicationReadyEvent
import org.springframework.context.event.EventListener
import org.springframework.stereotype.Component


@Component
class LoadOnStartService {

    @Autowired
    lateinit var sourceSystemRepo: SourceSystemRepo

    @Autowired
    lateinit var endpointsRepo: EndpointRepo

    @Autowired
    lateinit var reqresRepo: ReqResRepo

    @EventListener
    @Throws(Exception::class)
    fun appReadyTestMethod(event: ApplicationReadyEvent?) {

        // saving data in SourceSystem, Endpoint, RequestResponse
        val endpointConfigMLP = Endpoints()
        endpointConfigMLP.epid = 1
        endpointConfigMLP.blaze = true
        endpointConfigMLP.idm = true
        endpointConfigMLP.cibil = true
        endpointConfigMLP.liability = true

        val sourceSystem = SourceSystem()
        sourceSystem.ssid = 1
        sourceSystem.sourceAppName = "MLP"
        endpointsRepo.save(endpointConfigMLP)
        sourceSystemRepo.save(sourceSystem)

        val source: SourceSystem = sourceSystemRepo.findById(1).get()
        val endpoint: Endpoints = endpointsRepo.findById(1).get()
        val sourceId: Int? = source.ssid
        val endpointId: Int? = endpoint.epid

        val reqres = RequestResponse()
        reqres.epid = endpointId
        reqres.ssid = sourceId
        reqresRepo.save(reqres)
        println(reqres)

        // fetching rules for MLP
        val fetchForMLP: RequestResponse = reqresRepo.findById(1).get()
        val endpointsMLP: Endpoints? = fetchForMLP.epid?.let { endpointsRepo.findById(it).get() }

        // adding rules in camel context
        val routes = Router()

        var camelContext: DefaultCamelContext = DefaultCamelContext()
        if (endpointsMLP != null) {
            if (endpointsMLP.blaze!! && endpointsMLP.idm!! && endpointsMLP.cibil!! && endpointsMLP.liability!!) {
                camelContext.addRoutes(routes)
            }
        }
        camelContext.start()
    }
}