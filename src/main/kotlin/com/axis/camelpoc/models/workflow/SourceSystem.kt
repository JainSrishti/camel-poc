package com.axis.camelpoc.models.workflow

import javax.persistence.Id
import javax.persistence.Column
import javax.persistence.Entity
import javax.persistence.Table


//@Entity
//@Table(name = "source_systems")
class SourceSystem(//@Id @Column(name = "id")
                   private var id: String,

                   //@Column(name = "name", unique = true)
                   private var name: String,

                   //@Column(name = "app_code")
                   private var applicationCode: Int,

                   //@Column(name = "router_class")
                   private var sourceSystemRouter: String,

                   //@Column(name = "source_url")
                   private var sourceUrl: String,

                   private var endpoints: MutableList<Endpoints>) {

    fun getSourceSystemId(): String {
        return this.id
    }

    fun getSourceSystemName(): String {
        return this.name
    }

    fun getSourceSystemConnectionString(): Int {
        return this.applicationCode
    }

    fun getSourceSystemRouter(): String {
        return this.sourceSystemRouter
    }

    fun getSourceUrl(): String {
        return this.sourceUrl
    }

    fun getEndpoints(): MutableList<Endpoints> {
        return endpoints
    }
}