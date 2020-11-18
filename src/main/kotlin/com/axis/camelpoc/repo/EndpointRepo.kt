package com.axis.camelpoc.repo

import org.springframework.stereotype.Repository
import com.axis.camelpoc.models.others.Endpoints
import org.springframework.data.jpa.repository.JpaRepository

@Repository
interface EndpointRepo : JpaRepository<Endpoints?, Int?>