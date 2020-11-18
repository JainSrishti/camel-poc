package com.axis.camelpoc.config

import org.apache.camel.ProducerTemplate
import org.apache.camel.impl.DefaultCamelContext
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
class ProducerTemplateConfig {


    @Bean
    fun getCamelContext(): DefaultCamelContext {
        return DefaultCamelContext();
    }

    @Bean
    fun getProducerTemplate(camelContext: DefaultCamelContext): ProducerTemplate {
        return camelContext.createProducerTemplate()
    }
}