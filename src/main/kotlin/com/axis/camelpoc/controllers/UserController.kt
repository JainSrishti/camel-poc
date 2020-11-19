package com.axis.camelpoc.controllers

import com.axis.camelpoc.models.User
import org.apache.camel.CamelContext
import org.apache.camel.ConsumerTemplate
import org.apache.camel.ProducerTemplate
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestMethod
import org.springframework.web.bind.annotation.RestController

@RestController
class UserController(){

    var log: Logger = LoggerFactory.getLogger(UserController::class.java)
    @Autowired
    private lateinit var producerTemplate: ProducerTemplate

    @Autowired
    private lateinit var consumerTemplate: ConsumerTemplate

    @Autowired
    private lateinit var context: CamelContext

    @RequestMapping(value = ["/process/user"], method = [RequestMethod.POST])
    fun processUserRequest(@RequestBody user: User) {
        log.info("User$user")
        producerTemplate.sendBody("direct:start", user)
       /* return consumerTemplate.receiveBody("direct:result")*/
    }
}