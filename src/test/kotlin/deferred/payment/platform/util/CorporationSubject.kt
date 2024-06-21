package deferred.payment.platform.util

import deferred.payment.platform.corporation.domain.CorporateUser
import deferred.payment.platform.corporation.domain.Corporation
import java.math.BigDecimal

object CorporationSubject {
    fun create(
        id: Long = Random.createRandomPositiveLong(),
        name: String = Random.createRandomName(),
        creditLimit: BigDecimal = Random.createRandomPositiveBigDecimal(),
        outstandingBalance: BigDecimal = Random.createRandomPositiveBigDecimal(),
        corporateUsers: List<CorporateUser> = emptyList()
    ): Corporation {
        val corporation = Corporation(
            name = name,
            creditLimit = creditLimit,
            outstandingBalance = outstandingBalance,
        )
        return corporation
    }
}
