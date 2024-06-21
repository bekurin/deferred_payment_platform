package deferred.payment.platform.corporation.infrastructure

import deferred.payment.platform.corporation.domain.CorporateUser
import org.springframework.data.jpa.repository.JpaRepository

interface CorporateUserRepository : JpaRepository<CorporateUser, Long>
