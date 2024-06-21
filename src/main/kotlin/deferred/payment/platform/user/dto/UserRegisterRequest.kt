package deferred.payment.platform.user.dto

import deferred.payment.platform.user.domain.User
import java.math.BigDecimal

data class UserRegisterRequest(
    val name: String,
    val email: String,
    val phoneNumber: String,
    val creditLimit: BigDecimal,
) {
    fun toUser(): User {
        return User(
            name = name,
            email = email,
            phoneNumber = phoneNumber,
            creditLimit = creditLimit
        )
    }
}
