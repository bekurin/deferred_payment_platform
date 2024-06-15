package deferred.payment.platform.util

import deferred.payment.platform.AccountHolder
import deferred.payment.platform.billing.account.domain.BillingAccount
import deferred.payment.platform.billing.account.domain.Transaction
import java.math.BigDecimal
import java.time.LocalDate

object TransactionSubject {
    fun createUserTransaction(
        userId: Long = Random.createRandomPositiveLong(),
        amount: BigDecimal = Random.createRandomPositiveBigDecimal(),
        transactionDate: LocalDate = LocalDate.MAX,
        billingAccount: BillingAccount = BillingAccountSubject.createUserBillingAccount(userId = userId),
        today: LocalDate = LocalDate.now(),
    ): Transaction {
        return Transaction(
            accountHolder = AccountHolder(userId, null),
            amount = amount,
            transactionDate = transactionDate,
            billingAccount = billingAccount,
            today = today,
        )
    }

    fun createCorporateUserTransaction(
        corporationId: Long = Random.createRandomPositiveLong(),
        amount: BigDecimal = Random.createRandomPositiveBigDecimal(),
        transactionDate: LocalDate = LocalDate.MAX,
        billingAccount: BillingAccount = BillingAccountSubject.createCorporationBillingAccount(corporationId = corporationId),
        today: LocalDate = LocalDate.now(),
    ): Transaction {
        return Transaction(
            accountHolder = AccountHolder(null, corporationId),
            amount = amount,
            transactionDate = transactionDate,
            billingAccount = billingAccount,
            today = today,
        )
    }
}
