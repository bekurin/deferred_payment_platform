package deferred.payment.platform.corporation.dto

import deferred.payment.platform.corporation.domain.CorporateUserInfo
import java.math.BigDecimal

data class CorporateUserCreateRequest(
    val corporationId: Long,
    val name: String,
    val email: String,
    val phoneNumber: String,
    val creditLimit: BigDecimal
) {
    init {
        if (corporationId <= 0) {
            throw IllegalArgumentException()
        }
        if (name.isBlank()) {
            throw IllegalArgumentException()
        }
        if (phoneNumber.isBlank()) {
            throw IllegalArgumentException()
        }
        if (creditLimit <= BigDecimal.ZERO) {
            throw IllegalArgumentException()
        }
    }

    fun toCorporateUserInfo(): CorporateUserInfo {
        return CorporateUserInfo(
            name = name,
            email = email,
            phoneNumber = phoneNumber,
            creditLimit = creditLimit,
        )
    }
}
