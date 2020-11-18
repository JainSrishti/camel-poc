package com.axis.camelpoc.controllers

import com.axis.camelpoc.models.User
import org.apache.camel.ProducerTemplate
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestMethod
import org.springframework.web.bind.annotation.RestController

@RestController
class UserController(){

    /*@Autowired
    lateinit var producerTemplate: ProducerTemplate*/

    @RequestMapping(value = ["/process/user"], method = [RequestMethod.POST])
    fun processUserRequest(@RequestBody user: User) {

        //producerTemplate.sendBody("direct:start", user)


        //Consumer Template
    }
}