/*
package com.axis.camelpoc.models.workflow

import javax.persistence.*

@Entity
@Table(name = "external_integrator_configuration")
class ExternalIntegratorConfiguration(@Id @Column(name = "external_integrator_id")
                                      private var id: String,

                                      @Column(name = "external_integrator_connection_string")
                                      private var integratorEndpointConnectionString: String,

                                      @Column(name = "external_integrator_processor")
                                      private var integratorEndpointProcessor: String,

                                      @Column(name = "external_integrator_content_type")
                                      private var integratorEndpointContentType: String,

                                      @Column(name = "external_integrator_connection_type")
                                      private var integratorEndpointConnectionType: String,

                                      @Column(name = "external_integrator_connection_method")
                                      private var integratorEndpointConnectionMethod: String,

                                      @Column(name = "external_integrator_priority")
                                      private var integratorEndpointPriority: Int,

                                      @Column(name = "external_integrator_connection_timeout")
                                      private var integratorEndpointConnectionTimeout: Int,

                                      @Column(name = "external_integrator_read_timeout")
                                      private var integratorEndpointReadTimeout: Int) {

    fun getID(): String {
        return this.id
    }

    fun getIntegratorEndpointConnectionString(): String {
        return this.integratorEndpointConnectionString
    }

    fun getIntegratorEndpointProcessor(): String {
        return this.integratorEndpointProcessor
    }

    fun getIntegratorEndpointContentType(): String {
        return this.integratorEndpointContentType
    }

    fun getIntegratorEndpointConnectionType(): String {
        return this.integratorEndpointConnectionType
    }

    fun getIntegratorEndpointConnectionMethod(): String {
        return this.integratorEndpointConnectionMethod
    }

    fun getIntegratorEndpointPriority(): Int {
        return this.integratorEndpointPriority
    }

    fun getIntegratorEndpointConnectionTimeout(): Int {
        return this.integratorEndpointConnectionTimeout
    }

    fun getIntegratorEndpointReadTimeout(): Int {
        return this.integratorEndpointReadTimeout
    }

    override fun toString(): String {

        return "ExternalIntegratorConfiguration(id='$id', " +
                "integratorEndpointConnectionString='$integratorEndpointConnectionString', " +
                "integratorEndpointProcessor='$integratorEndpointProcessor', " +
                "integratorEndpointContentType='$integratorEndpointContentType', " +
                "integratorEndpointConnectionType='$integratorEndpointConnectionType', " +
                "integratorEndpointConnectionMethod='$integratorEndpointConnectionMethod', " +
                "integratorEndpointPriority=$integratorEndpointPriority, " +
                "integratorEndpointConnectionTimeout=$integratorEndpointConnectionTimeout, " +
                "integratorEndpointReadTimeout=$integratorEndpointReadTimeout)"
    }


}*/
