package deferred.payment.platform.corporation.dto

import deferred.payment.platform.corporation.domain.Corporation
import java.math.BigDecimal

data class CorporationResponse(
    val id: Long,
    val name: String,
    val creditLimit: BigDecimal,
    val outstandingBalance: BigDecimal
) {
    constructor(corporation: Corporation) : this(
        id = corporation.id,
        name = corporation.name,
        creditLimit = corporation.creditLimit,
        outstandingBalance = corporation.outstandingBalance
    )
}
