package deferred.payment.platform.corporation.dto

import deferred.payment.platform.corporation.domain.CorporateUser
import java.math.BigDecimal

data class CorporateUserResponse(
    val id: Long,
    val name: String,
    val email: String,
    val phoneNumber: String,
    val creditLimit: BigDecimal,
    val corporationName: String,
) {
    constructor(corporateUser: CorporateUser) : this(
        id = corporateUser.id,
        name = corporateUser.name,
        email = corporateUser.email,
        phoneNumber = corporateUser.phoneNumber,
        creditLimit = corporateUser.creditLimit,
        corporationName = corporateUser.corporation.name,
    )
}
