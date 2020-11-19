package com.axis.camelpoc.processors.cibil

import com.axis.camelpoc.models.User
import org.apache.camel.Exchange
import org.apache.camel.Processor
import com.axis.camelpoc.models.requests.CibilRequest
import com.axis.camelpoc.processors.idm.IdmRequestProcessor
import com.fasterxml.jackson.databind.ObjectMapper
import org.apache.camel.component.netty.http.NettyHttpMessage
import org.slf4j.Logger
import org.slf4j.LoggerFactory

class CibilRequestProcessor(private val objectMapper: ObjectMapper): Processor {

    var log: Logger = LoggerFactory.getLogger(CibilRequestProcessor::class.java)
    
    override fun process(exchange: Exchange?) {

        val message = exchange?.getIn(NettyHttpMessage::class.java)
        val str1: String? = message?.getBody(String::class.java)

        log.info("User in Cibil Request processor: $str1")
        
        val str: String = "[{\n" +
                "    \"FNAME\": \"Shashanka\",\n" +
                "    \"DOB_DATE\": \"05-07-1985\",\n" +
                "    \"SEX\": \"M\",\n" +
                "    \"ADDRESS1\": \"Whitefield,Bangalore\",\n" +
                "    \"STATE\": \"Karnataka\",\n" +
                "    \"ZIPCODE\": \"561007\",\n" +
                "    \"PAN_NO\": \"ER547937\",\n" +
                "    \"PRODUCT_CODE\": \"T6456\",\n" +
                "    \"MOBILE_NO\": \"9583107583\",\n" +
                "    \"LOAN_AMOUNT\": \"300000\"\n" +
                "}]"
        exchange?.getIn()?.setBody(str, String::class.java)
    }
}