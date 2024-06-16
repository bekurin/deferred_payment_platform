package deferred.payment.platform.util

import deferred.payment.platform.user.domain.User
import java.math.BigDecimal

object UserSubject {
    fun create(
        name: String = Random.createRandomName(),
        email: String = Random.createRandomEmail(),
        phoneNumber: String = Random.createRandomPhoneNumber(),
        creditLimit: BigDecimal = Random.createRandomPositiveBigDecimal(),
        outstandingBalance: BigDecimal = BigDecimal.ZERO,
    ): User {
        return User(
            name = name,
            email = email,
            phoneNumber = phoneNumber,
            creditLimit = creditLimit,
            outstandingBalance = outstandingBalance,
        )
    }
}
