package deferred.payment.platform.util

import deferred.payment.platform.corporation.domain.CorporateUser
import deferred.payment.platform.corporation.domain.Corporation
import java.math.BigDecimal

object CorporateUserSubject {
    fun create(
        name: String = Random.createRandomName(),
        email: String = Random.createRandomEmail(),
        phoneNumber: String = Random.createRandomPhoneNumber(),
        corporation: Corporation = CorporationSubject.create(),
        creditLimit: BigDecimal = Random.createRandomPositiveBigDecimal(),
    ): CorporateUser {
        return CorporateUser(
            name = name,
            email = email,
            phoneNumber = phoneNumber,
            corporation = corporation,
            creditLimit = creditLimit,
        )
    }
}
