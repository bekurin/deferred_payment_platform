package deferred.payment.platform.billing.account.domain

import deferred.payment.platform.BaseEntity
import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.JoinColumn
import jakarta.persistence.OneToOne
import jakarta.persistence.Table
import java.time.LocalDate

@Entity
@Table(name = "payment")
class Payment(
    paymentDate: LocalDate,
    transaction: Transaction,
) : BaseEntity() {
    @Column(name = "payment_date", nullable = false, updatable = false)
    var paymentDate: LocalDate = paymentDate
        protected set

    @OneToOne
    @JoinColumn(name = "transaction_id", updatable = false)
    var transaction: Transaction = transaction
        protected set
}
