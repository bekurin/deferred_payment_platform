package deferred.payment.platform.corporation.domain

import org.springframework.data.jpa.repository.JpaRepository

interface CorporationRepository : JpaRepository<Corporation, Long>
