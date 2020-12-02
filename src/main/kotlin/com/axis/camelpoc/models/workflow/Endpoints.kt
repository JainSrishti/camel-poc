package com.axis.camelpoc.models.workflow

import javax.persistence.*

//@Entity
//@Table(name = "endpoints")
class Endpoints(//@Id @Column(name = "id")
                private var id: String,

                //@Column(name = "route_name", unique = true)
                private var routeName: String,

                //@Column(name = "url")
                private var url: String,

                //@Column(name = "content_type")
                private var contentType: String,

                //@Column(name = "request_type")
                private var requestType: String,

                //@Column(name = "request_method")
                private var requestMethod: String,

                //@Column(name = "processor")
                private var processor: String,

                //@Column(name = "aggregator")
                private var aggregator: String,

                //@Column(name = "exception")
                private var exception: String,

                //@Column(name = "connection_timeout")
                private var connectionTimeout: Int,

                //@Column(name = "priority")
                private var priority: Int,

                //@Column(name = "mandatory")
                private var mandatory: Boolean) {

    fun getEndpointId(): String {
        return this.id
    }

    fun getEndpointRouteName(): String {
        return this.routeName
    }

    fun getEndpointUrl(): String {
        return this.url
    }

    fun getEndpointProcessor(): String {
        return this.processor
    }

    fun getEndpointAggregator(): String {
        return this.aggregator
    }

    fun getEndpointException(): String {
        return this.exception
    }

    fun getEndpointContentType(): String {
        return this.contentType
    }

    fun getEndpointRequestType(): String {
        return this.requestType
    }

    fun getEndpointRequestMethod(): String {
        return this.requestMethod
    }

    fun getEndpointConnectionTimeout(): Int {
        return this.connectionTimeout
    }

    fun getEndpointPriority(): Int {
        return this.priority
    }

    fun isEndpointMandatory(): Boolean {
        return this.mandatory;
    }
}