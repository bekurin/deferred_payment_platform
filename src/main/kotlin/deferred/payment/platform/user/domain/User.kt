package deferred.payment.platform.user.domain

import deferred.payment.platform.BaseEntity
import deferred.payment.platform.billing.account.domain.Transaction
import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.Table
import java.math.BigDecimal

@Entity
@Table(name = "individual_user")
class User(
    name: String,
    email: String,
    phoneNumber: String,
    creditLimit: BigDecimal,
    outstandingBalance: BigDecimal = BigDecimal.ZERO,
) : BaseEntity() {
    @Column(name = "name", nullable = false)
    var name: String = name
        protected set

    @Column(name = "email", nullable = false)
    var email: String = email
        protected set

    @Column(name = "phone_number", nullable = false)
    var phoneNumber: String = phoneNumber
        protected set

    @Column(name = "credit_limit", nullable = false)
    var creditLimit: BigDecimal = creditLimit
        protected set

    @Column(name = "outstanding_balance", nullable = false)
    var outstandingBalance: BigDecimal = outstandingBalance
        protected set

    fun validateCreditLimitExceeded(transactions: List<Transaction>) {
        val totalAmount = transactions.sumOf { it.amount }
        if (totalAmount + outstandingBalance > creditLimit) {
            throw IllegalArgumentException()
        }
    }
}
