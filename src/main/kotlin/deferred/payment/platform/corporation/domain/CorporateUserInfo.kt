package deferred.payment.platform.corporation.domain

import java.math.BigDecimal

data class CorporateUserInfo(
    val name: String,
    val email: String,
    val phoneNumber: String,
    val creditLimit: BigDecimal,
)
