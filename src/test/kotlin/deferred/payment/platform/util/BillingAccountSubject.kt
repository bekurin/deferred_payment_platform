package deferred.payment.platform.util

import deferred.payment.platform.billing.account.domain.BillingAccount
import deferred.payment.platform.billing.account.domain.BillingAccountHolder
import java.time.LocalDate

object BillingAccountSubject {
    fun createUserBillingAccount(
        userId: Long = Random.createRandomPositiveLong(),
        billingAddress: String = Random.createRandomString(),
        cardNumber: String = Random.createRandomString(),
        cardExpiryDate: LocalDate = LocalDate.MAX,
        cardCsv: String = "${Random.createRandomDigits(3)}",
    ): BillingAccount {
        return BillingAccount(
            billingAccountHolder = BillingAccountHolder(userId, null),
            billingAddress = billingAddress,
            cardNumber = cardNumber,
            cardExpiryDate = cardExpiryDate,
            cardCsv = cardCsv,
        )
    }

    fun createCorporationBillingAccount(
        corporationId: Long = Random.createRandomPositiveLong(),
        billingAddress: String = Random.createRandomString(),
        cardNumber: String = Random.createRandomString(),
        cardExpiryDate: LocalDate = LocalDate.MAX,
        cardCsv: String = "${Random.createRandomDigits(3)}",
    ): BillingAccount {
        return BillingAccount(
            billingAccountHolder = BillingAccountHolder(null, corporationId),
            billingAddress = billingAddress,
            cardNumber = cardNumber,
            cardExpiryDate = cardExpiryDate,
            cardCsv = cardCsv,
        )
    }
}
