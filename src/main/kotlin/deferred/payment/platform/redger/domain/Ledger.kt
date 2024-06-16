package deferred.payment.platform.redger.domain

import deferred.payment.platform.BaseEntity
import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.Table
import java.math.BigDecimal
import java.time.LocalDateTime

@Entity
@Table(name = "ledger")
class Ledger(
    transactionId: Long,
    amount: BigDecimal,
    type: LedgerType,
) : BaseEntity() {
    @Column(name = "transaction_id")
    var transactionId: Long = transactionId
        protected set

    @Column(name = "amount", nullable = false)
    var amount: BigDecimal = amount
        protected set

    @Column(name = "type", nullable = false)
    var type: LedgerType = type
        protected set

    @Column(nullable = false, updatable = false)
    var deletedAt: LocalDateTime? = null
        protected set

    fun delete(now: LocalDateTime): Ledger {
        this.deletedAt = now
        return this
    }

    enum class LedgerType(description: String) {
        DEPOSIT("결제 예정 금액"),
        WITHDRAWAL("결제 완료 금액"),
    }
}
