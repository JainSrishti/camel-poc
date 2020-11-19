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

   /* @Autowired
    lateinit var camelContext: DefaultCamelContext*/
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
       val endpointConfigFlipkart = Endpoints()
        endpointConfigFlipkart.blaze = true
        endpointConfigFlipkart.sequential = true
        endpointConfigFlipkart.creditCard =true
        endpointConfigFlipkart.epid= 1
        endpointConfigFlipkart.creditVidya=true

        val sourceSystem = SourceSystem()
        sourceSystem.ssid= 1
        sourceSystem.sourceAppName="Flipkart"
        endpointsRepo.save(endpointConfigFlipkart)
        sourceSystemRepo.save(sourceSystem)

        val endpointConfigAmazon = Endpoints()
        val sourceSystemAmaz = SourceSystem()
        endpointConfigAmazon.creditVidya=true
        endpointConfigAmazon.creditCard=true
        endpointConfigAmazon.epid=2
        endpointConfigAmazon.sequential=false
        endpointConfigAmazon.blaze=true
        sourceSystemAmaz.ssid=2
        sourceSystemAmaz.sourceAppName="Amazon"
        endpointsRepo.save(endpointConfigAmazon)
        sourceSystemRepo.save(sourceSystemAmaz)
        val source: SourceSystem = sourceSystemRepo.findById(1).get()
        val endpoint: Endpoints = endpointsRepo.findById(1).get()
        val sourceId: Int? = source.ssid
        val endpointId: Int? = endpoint.epid

        val reqres = RequestResponse()
        reqres.epid= endpointId
        reqres.ssid= sourceId
        reqresRepo.save(reqres)
        println(reqres)

        val sourceTwo: SourceSystem = sourceSystemRepo.findById(2).get()
        val endpointTwo: Endpoints = endpointsRepo.findById(2).get()
        val sourceIdTwo: Int? = sourceTwo.ssid
        val endpointIdTwo: Int? = endpointTwo.epid

        val reqresponse = RequestResponse()
        reqresponse.epid= endpointIdTwo
        reqresponse.ssid= sourceIdTwo
        reqresRepo.save(reqresponse)

        // fetching rules for flipkart and amazon
        val fetchForFlipkart: RequestResponse = reqresRepo.findById(1).get()
        val endpointsFlipkart: Endpoints? = fetchForFlipkart.epid?.let { endpointsRepo.findById(it).get() }


        // adding rules in camel context

        val routes = Router()
        val objectMapper = ObjectMapper()
        var camelContext: DefaultCamelContext = DefaultCamelContext()
        //routes.objectMapper=objectMapper
        /*if (endpointsFlipkart != null) {
            if (endpointsFlipkart.blaze!! && endpointsFlipkart.creditCard!! && endpointsFlipkart.creditVidya!!) {
                camelContext.addRoutes(routes)
            }
        }*/
        camelContext.addRoutes(routes)
        camelContext.start()
    }
}