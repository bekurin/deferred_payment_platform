package deferred.payment.platform.billing.account.domain

import deferred.payment.platform.util.TestConstant
import deferred.payment.platform.util.TransactionSubject
import java.time.LocalDate
import kotlin.test.Test
import org.assertj.core.api.SoftAssertions

class PaymentTest {
    @Test
    fun `결제 성공 내역이 적재된다`() {
        // given
        val paymentDate = LocalDate.now()
        val transaction = TransactionSubject.createUserTransaction()

        // when
        val payment =
            Payment(
                paymentDate = paymentDate,
                transaction = transaction,
            )

        // then
        SoftAssertions.assertSoftly { softly ->
            softly.assertThat(payment.paymentDate).isEqualTo(paymentDate)
            softly.assertThat(payment.transaction)
                .usingRecursiveAssertion()
                .ignoringFields(*TestConstant.IGNORE_FIELDS.toTypedArray())
                .isEqualTo(transaction)
        }
    }
}
