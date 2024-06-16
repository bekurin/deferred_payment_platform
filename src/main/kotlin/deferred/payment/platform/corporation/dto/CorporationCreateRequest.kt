package deferred.payment.platform.corporation.dto

import deferred.payment.platform.corporation.domain.Corporation
import jakarta.validation.constraints.DecimalMin
import jakarta.validation.constraints.NotBlank
import java.math.BigDecimal

data class CorporationCreateRequest(
    @get:NotBlank
    val name: String,
    @get:DecimalMin("0.0")
    val creditLimit: BigDecimal,
) {
    init {
        if (name.isBlank()) {
            throw IllegalArgumentException()
        }
        if (creditLimit <= BigDecimal.ZERO) {
            throw IllegalArgumentException()
        }
    }

    fun toCorporation(): Corporation {
        return Corporation(
            name = name,
            creditLimit = creditLimit,
        )
    }
}
