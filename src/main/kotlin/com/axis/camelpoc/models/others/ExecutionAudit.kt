package com.axis.camelpoc.models.others

import javax.persistence.*

@Entity
class ExecutionAudit {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private val reqResId: Int? = null

    @Column
    var ssid: Int? = null

    @Column
    var orderResponse: String? = null

    @Column
    var ordersRequest: String? = null

    override fun toString(): String {
        return "ExecutionAudit{" +
                "ssid=" + ssid +
                ", orderResponse='" + orderResponse + '\'' +
                ", ordersRequest='" + ordersRequest + '\'' +
                '}'
    }
}