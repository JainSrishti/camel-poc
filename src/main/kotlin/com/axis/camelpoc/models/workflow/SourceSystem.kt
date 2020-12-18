package com.axis.camelpoc.models.workflow


//@Entity
//@Table(name = "source_systems")
class SourceSystem(//@Id @Column(name = "id")
        private var id: Int,

        //@Column(name = "name", unique = true)
        private var name: String,

        //@Column(name = "app_code")
        private var applicationCode: Int,

        //@Column(name = "router_class")
        private var sourceSystemRouter: String,

        private var endpoints: MutableList<Endpoints>,

        private var workflows: MutableList<Workflow>) {

    fun getSourceSystemId(): Int {
        return this.id
    }

    fun getSourceSystemName(): String {
        return this.name
    }

    fun getSourceSystemApplicationCode(): Int {
        return this.applicationCode
    }

    fun getSourceSystemRouter(): String {
        return this.sourceSystemRouter
    }

    fun getEndpoints(): MutableList<Endpoints> {
        return endpoints
    }

    fun getWorkflow(): MutableList<Workflow> {
        return workflows
    }
}