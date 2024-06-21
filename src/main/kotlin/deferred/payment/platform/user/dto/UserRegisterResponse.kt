package deferred.payment.platform.user.dto

import deferred.payment.platform.user.domain.User
import java.math.BigDecimal

data class UserRegisterResponse(
    val id: Long,
    val name: String,
    val email: String,
    val phoneNumber: String,
    val creditLimit: BigDecimal,
    val outstandingBalance: BigDecimal,
) {
    constructor(user: User) : this(
        id = user.id,
        name = user.name,
        email = user.email,
        phoneNumber = user.phoneNumber,
        creditLimit = user.creditLimit,
        outstandingBalance = user.outstandingBalance,
    )
}
