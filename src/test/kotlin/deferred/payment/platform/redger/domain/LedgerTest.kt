package deferred.payment.platform.redger.domain

import deferred.payment.platform.redger.domain.Ledger.LedgerType
import deferred.payment.platform.util.Random
import java.time.LocalDateTime
import org.assertj.core.api.Assertions.assertThat
import org.assertj.core.api.SoftAssertions
import org.junit.jupiter.api.Nested
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.EnumSource

class LedgerTest {
    @Nested
    inner class `원장 정보를 생성할 때` {
        @ParameterizedTest
        @EnumSource(LedgerType::class)
        fun `결제 예정, 결제 완료 금액 정보를 생성할 수 있다`(ledgerType: LedgerType) {
            // given
            val transactionId = Random.createRandomPositiveLong()
            val amount = Random.createRandomPositiveBigDecimal()

            // when
            val ledger = Ledger(
                transactionId = transactionId,
                amount = amount,
                type = ledgerType,
            )

            // then
            SoftAssertions.assertSoftly { softly ->
                softly.assertThat(ledger.amount).isEqualTo(amount)
                softly.assertThat(ledger.transactionId).isEqualTo(transactionId)
                softly.assertThat(ledger.type).isEqualTo(ledgerType)
                softly.assertThat(ledger.deletedAt).isNull()
            }
        }
    }

    @ParameterizedTest
    @EnumSource(LedgerType::class)
    fun `원장 정보를 soft delete 할 수 있다`(ledgerType: LedgerType) {
        // given
        val transactionId = Random.createRandomPositiveLong()
        val amount = Random.createRandomPositiveBigDecimal()
        val ledger =
            Ledger(
                transactionId = transactionId,
                amount = amount,
                type = ledgerType,
            )
        val now = LocalDateTime.now()

        // when
        val deletedLedger = ledger.delete(now)

        // then
        assertThat(deletedLedger.deletedAt).isEqualTo(now)
    }
}
