package com.axis.camelpoc.repo
import com.axis.camelpoc.models.others.RequestResponse
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface ReqResRepo : JpaRepository<RequestResponse?, Int?>