package com.axis.camelpoc.models.others

import javax.persistence.Column
import javax.persistence.Entity
import javax.persistence.Id
import javax.persistence.Table

@Entity
@Table(name = "endpoint")
class Endpoints {
    @Id
    var epid: Int? = null

    @Column
    var cibil: Boolean? = null

    @Column
    var liability: Boolean? = null

    @Column
    var idm: Boolean? = null

    @Column
    var blaze: Boolean? = null

    override fun toString(): String {
        return "Endpoints{" +
                "epid=" + epid +
                ", creditCard=" + cibil +
                ", creditVidya=" + liability +
                ", isSequential=" + idm +
                ", isBlaze=" + blaze +
                '}'
    }
}