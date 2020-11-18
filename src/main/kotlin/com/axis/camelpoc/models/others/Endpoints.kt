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
    var creditCard: Boolean? = null

    @Column
    var creditVidya: Boolean? = null

    @Column
    var sequential: Boolean? = null

    @Column
    var blaze: Boolean? = null

    override fun toString(): String {
        return "Endpoints{" +
                "epid=" + epid +
                ", creditCard=" + creditCard +
                ", creditVidya=" + creditVidya +
                ", isSequential=" + sequential +
                ", isBlaze=" + blaze +
                '}'
    }
}