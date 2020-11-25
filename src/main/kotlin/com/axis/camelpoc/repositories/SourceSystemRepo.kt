package com.axis.camelpoc.repositories

import com.axis.camelpoc.models.others.SourceSystem
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository


@Repository
interface SourceSystemRepo : JpaRepository<SourceSystem?, Int?>