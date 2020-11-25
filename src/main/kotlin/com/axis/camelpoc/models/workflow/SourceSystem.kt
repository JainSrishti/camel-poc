package com.axis.camelpoc.models.workflow

import javax.persistence.Id
import javax.persistence.Column
import javax.persistence.Entity
import javax.persistence.Table


//@Entity
//@Table(name = "source_system")
class SourceSystem(//@Id @Column(name = "source_system_id")
                   private var sourceSystemId: String,

                   //@Column(name = "source_system_name")
                   private var sourceSystemName: String,

                   //@Column(name = "source_system_connection_string")
                   private var sourceSystemConnectionString: String,

                   //@Column(name = "source_system_router")
                   private var sourceSystemRouter: String,

                   //@Column(name = "source_system_connection_type")
                   private var sourceSystemConnectionType: String,

                   //@Column(name = "source_system_connection_method")
                   private var sourceSystemConnectionMethod: String,

                   //@Column(name = "source_system_connection_timeout")
                   private var sourceSystemConnectionTimeout: Int,

                   //@Column(name = "source_system_read_timeout")
                   private var sourceSystemReadTimeout: Int,

                   private var endpoints: MutableList<ExternalIntegratorAdapters>) {

    fun getSourceSystemId(): String {
        return this.sourceSystemId
    }

    fun getSourceSystemName(): String {
        return this.sourceSystemName
    }

    fun getSourceSystemConnectionString(): String {
        return this.sourceSystemConnectionString
    }

    fun getSourceSystemRouter(): String {
        return this.sourceSystemRouter
    }

    fun getEndpoints(): MutableList<ExternalIntegratorAdapters> {
        return endpoints
    }
    override fun toString(): String {

        return "SourceSystem(sourceSystemId='$sourceSystemId', " +
                "sourceSystemName='$sourceSystemName', " +
                "sourceSystemConnectionString='$sourceSystemConnectionString', " +
                "sourceSystemRouter='$sourceSystemRouter', " +
                "sourceSystemConnectionType='$sourceSystemConnectionType', " +
                "sourceSystemConnectionMethod='$sourceSystemConnectionMethod', " +
                "sourceSystemConnectionTimeout=$sourceSystemConnectionTimeout, " +
                "sourceSystemReadTimeout=$sourceSystemReadTimeout)"
    }


}