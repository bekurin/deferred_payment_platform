package deferred.payment.platform.corporation.domain

import org.springframework.data.jpa.repository.JpaRepository

interface CorporateUserRepository : JpaRepository<CorporateUser, Long>
