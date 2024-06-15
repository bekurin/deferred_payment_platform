package deferred.payment.platform.billing.account.domain

import deferred.payment.platform.util.Random
import java.time.LocalDate
import org.assertj.core.api.SoftAssertions
import org.junit.jupiter.api.Test

class BillingAccountTest {
    @Test
    fun `개인 결제 수단을 생성할 수 있다`() {
        // given
        val userId = Random.createRandomPositiveLong()

        // when
        val billingAccount = BillingAccount(
            billingAccountHolder = BillingAccountHolder(userId, null),
            billingAddress = Random.createRandomString(),
            cardNumber = Random.createRandomCarNumber(),
            cardExpiryDate = LocalDate.now().plusYears(1),
            cardCsv = Random.createRandomDigits(3).toString(),
        )

        // then
        SoftAssertions.assertSoftly { softly ->
            softly.assertThat(billingAccount.billingAccountHolder.corporationId).isNull()
            softly.assertThat(billingAccount.billingAccountHolder.userId).isEqualTo(userId)
        }
    }

    @Test
    fun `기업 대표 결제로 결제 수단을 생성할 수 있다`() {
        // given
        val corporationId = Random.createRandomPositiveLong()

        // when
        val billingAccount = BillingAccount(
            billingAccountHolder = BillingAccountHolder(null, corporationId),
            billingAddress = Random.createRandomString(),
            cardNumber = Random.createRandomCarNumber(),
            cardExpiryDate = LocalDate.now().plusYears(1),
            cardCsv = Random.createRandomDigits(3).toString(),
        )

        // then
        SoftAssertions.assertSoftly { softly ->
            softly.assertThat(billingAccount.billingAccountHolder.userId).isNull()
            softly.assertThat(billingAccount.billingAccountHolder.corporationId).isEqualTo(corporationId)
        }
    }
}
