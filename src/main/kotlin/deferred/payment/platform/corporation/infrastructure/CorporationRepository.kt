package deferred.payment.platform.corporation.infrastructure

import deferred.payment.platform.corporation.domain.Corporation
import org.springframework.data.jpa.repository.JpaRepository

interface CorporationRepository : JpaRepository<Corporation, Long>
