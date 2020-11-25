package com.axis.camelpoc.models.workflow

import javax.persistence.*

//@Entity
//@Table(name = "external_integrators")
class ExternalIntegratorAdapters(//@Id @Column(name = "external_integrator_id")
                                 private var externalIntegratorId: String,

                                 //@Column(name = "external_integrator_name")
                                 private var externalIntegratorName: String,

                                 //@Column(name = "external_integrator_connection_string")
                                 private var integratorEndpointConnectionString: String,

                                 //@Column(name = "external_integrator_processor")
                                 private var integratorEndpointProcessor: String,

                                 //@Column(name = "external_integrator_content_type")
                                 private var integratorEndpointContentType: String,

                                 //@Column(name = "external_integrator_connection_type")
                                 private var integratorEndpointConnectionType: String,

                                 //@Column(name = "external_integrator_connection_method")
                                 private var integratorEndpointConnectionMethod: String,

                                 //@Column(name = "external_integrator_priority")
                                 private var integratorEndpointPriority: Int,

                                 //@Column(name = "external_integrator_connection_timeout")
                                 private var integratorEndpointConnectionTimeout: Int,

                                 //@Column(name = "external_integrator_read_timeout")
                                 private var integratorEndpointReadTimeout: Int
           /*
                                 @OneToOne(cascade = [CascadeType.MERGE])
                                 @PrimaryKeyJoinColumn
                                 private var externalIntegratorConfiguration: ExternalIntegratorConfiguration*/) {



    fun getIntegratorEndpointConnectionString(): String {
        return this.integratorEndpointConnectionString
    }

    fun getIntegratorEndpointProcessor(): String {
        return this.integratorEndpointProcessor
    }

    fun getName(): String {
        return this.externalIntegratorName
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
}