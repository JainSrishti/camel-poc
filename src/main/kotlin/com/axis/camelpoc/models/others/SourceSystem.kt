package com.axis.camelpoc.models.others

import javax.persistence.*

@Entity
@Table(name = "source_system")
class SourceSystem {
    @Id
    var ssid: Int? = null

    @Column
    var sourceAppName: String? = null

    @OneToOne(cascade = [CascadeType.ALL])
    @JoinTable(name = "source_endpoint_mapping", joinColumns = [JoinColumn(referencedColumnName = "ssid")], inverseJoinColumns = [JoinColumn(referencedColumnName = "epid")])
    private val endpoints: Endpoints? = null
    override fun toString(): String {
        return "SourceSystem [ssid=$ssid, sourceAppName=$sourceAppName]"
    }
}