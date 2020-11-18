package com.axis.camelpoc.models.others

import javax.persistence.Column
import javax.persistence.Entity
import javax.persistence.Id

@Entity
class RequestResponse {
    @Id
    var ssid: Int? = null

    @Column
    var epid: Int? = null

    override fun toString(): String {
        return "RequestResponse [ssid=$ssid, epid=$epid]"
    }
}