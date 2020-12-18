package com.axis.camelpoc.models.workflow

import javax.persistence.Column
import javax.persistence.GeneratedValue
import javax.persistence.GenerationType
import javax.persistence.Id

//@Entity
//@Table(name = "workflow")
class Workflow(
        //@Id @Column(name = "id")
        //@GeneratedValue(strategy = GenerationType.AUTO)
        private val id: Int,

        //@Column(name = "start")
        private val start: String,

        //@Column(name = "end")
        private val end: String,

        //@Column(name = "failover_endpoint")
        private val failoverEndpoint: String?,

        //@Column(name = "priority")
        private val priority: Int = Int.MAX_VALUE) {

    fun getId(): Int {
        return this.id
    }

    fun getStart(): String {
        return this.start
    }

    fun getEnd(): String {
        return this.end
    }

    fun getFailoverEndpoint(): String? {
        return this.failoverEndpoint
    }

    fun getPriority(): Int{
        return this.priority
    }
}