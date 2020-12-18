package com.axis.camelpoc.models.workflow

//@Entity
//@Table(name = "endpoints")
class Endpoints(
        //@Id @Column(name = "id")
        private var id: String,

        //@Column(name = "name")
        private val endpointName: String,

        //@Column(name = "route_name", unique = true)
        private var routeName: String,

        //@Column(name = "route_type")
        private var routeType: String,

        //@Column(name = "url")
        private var url: String,

        //@Column(name = "content_type")
        private var contentType: String,

        //@Column(name = "request_type")
        private var requestType: String,

        //@Column(name = "request_method")
        private var requestMethod: String,

        //@Column(name = "request_processor")
        private var requestProcessor: String?,

        //@Column(name = "response_processor")
        private var responseProcessor: String?,

        //@Column(name = "aggregator")
        private var aggregator: String?,

        //@Column(name = "exception")
        private var exception: String?,

        //@Column(name = "maximum_request_count")
        private val maximumRequestCount: Long,

        //@Column(name = "connection_timeout")
        private var connectionTimeout: Int) {

    fun getEndpointId(): String {
        return this.id
    }

    fun getEndpointName(): String {
        return this.endpointName
    }

    fun getEndpointRouteName(): String {
        return this.routeName
    }

    fun getRouteType(): String {
        return this.routeType
    }

    fun getUrl(): String {
        return this.url
    }

    fun getRequestProcessor(): String? {
        return this.requestProcessor
    }

    fun getResponseProcessor(): String? {
        return this.responseProcessor
    }

    fun getEndpointAggregator(): String? {
        return this.aggregator
    }

    fun getEndpointException(): String? {
        return this.exception
    }

    fun getEndpointContentType(): String {
        return this.contentType
    }

    fun getRequestType(): String {
        return this.requestType
    }

    fun getRequestMethod(): String {
        return this.requestMethod
    }

    fun getMaximumRequestCount(): Long {
        return this.maximumRequestCount
    }

    fun getConnectionTimeout(): Int {
        return this.connectionTimeout
    }
}