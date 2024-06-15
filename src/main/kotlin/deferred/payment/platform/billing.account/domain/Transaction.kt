package deferred.payment.platform.billing.account.domain

import deferred.payment.platform.AccountHolder
import deferred.payment.platform.BaseEntity
import deferred.payment.platform.billing.account.domain.Transaction.TransactionStatus.PENDING
import deferred.payment.platform.redger.domain.Ledger
import deferred.payment.platform.redger.domain.Ledger.LedgerType.DEPOSIT
import deferred.payment.platform.redger.domain.Ledger.LedgerType.WITHDRAWAL
import jakarta.persistence.Column
import jakarta.persistence.Embedded
import jakarta.persistence.Entity
import jakarta.persistence.JoinColumn
import jakarta.persistence.ManyToOne
import jakarta.persistence.Table
import java.math.BigDecimal
import java.time.LocalDate

@Entity
@Table(name = "transaction")
class Transaction(
    accountHolder: AccountHolder,
    amount: BigDecimal,
    transactionDate: LocalDate,
    billingAccount: BillingAccount,
    today: LocalDate,
) : BaseEntity() {
    init {
        if (today >= transactionDate) {
            throw IllegalArgumentException()
        }
    }

    @Embedded
    var accountHolder: AccountHolder = accountHolder
        protected set

    @Column(name = "amount", nullable = false)
    var amount: BigDecimal = amount
        protected set

    @Column(name = "transaction_date", nullable = false)
    var transactionDate: LocalDate = transactionDate
        protected set

    @Column(name = "status")
    var status: TransactionStatus = PENDING
        protected set

    @ManyToOne
    @JoinColumn(name = "billing_account_id")
    var billingAccount: BillingAccount = billingAccount
        protected set

    fun generateDepositLedger(): Ledger {
        if (status != PENDING) {
            throw IllegalArgumentException()
        }

        return Ledger(
            transactionId = this.id,
            amount = amount,
            type = DEPOSIT,
        )
    }

    fun complete(paymentDate: LocalDate): Pair<Ledger, Payment> {
        if (status != TransactionStatus.COMPLETED) {
            throw IllegalArgumentException()
        }
        val ledger = Ledger(
            transactionId = this.id,
            amount = amount,
            type = WITHDRAWAL,
        )

        val payment = Payment(
            paymentDate = paymentDate,
            transaction = this,
        )
        return Pair(ledger, payment)
    }

    enum class TransactionStatus(description: String) {
        PENDING("결제 처리 전"),
        PROCESSING("결제 처리 중"),
        COMPLETED("결제 완료"),
        CANCELLED("결제 실패"),
    }
}
