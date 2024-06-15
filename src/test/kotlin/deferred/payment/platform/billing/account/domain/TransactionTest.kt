package deferred.payment.platform.billing.account.domain

import deferred.payment.platform.AccountHolder
import deferred.payment.platform.util.BillingAccountSubject
import deferred.payment.platform.util.Random
import java.time.LocalDate
import org.junit.jupiter.api.Nested
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertDoesNotThrow
import org.junit.jupiter.api.assertThrows
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.ValueSource

class TransactionTest {
    @Nested
    inner class `거래 정보를 생성할 때` {
        @Test
        fun `결제 예정 일자 미래면 생성에 성공한다`() {
            // given
            val (userId, today) = Pair(Random.createRandomPositiveLong(), LocalDate.now())
            val transactionDate = today.plusDays(1)

            // when
            val action = {
                Transaction(
                    accountHolder = AccountHolder(userId, null),
                    amount = Random.createRandomPositiveBigDecimal(),
                    transactionDate = transactionDate,
                    billingAccount = BillingAccountSubject.createUserBillingAccount(userId = userId),
                    today = today,
                )
            }

            // then
            assertDoesNotThrow(action)
        }

        @ParameterizedTest
        @ValueSource(longs = [0, 1])
        fun `결제 예정 일자 과거거나 오늘이면 생성에 실패한다`(daysToSubtract: Long) {
            // given
            val (userId, today) = Pair(Random.createRandomPositiveLong(), LocalDate.now())
            val transactionDate = today.minusDays(daysToSubtract)

            // when
            val action = {
                Transaction(
                    accountHolder = AccountHolder(userId, null),
                    amount = Random.createRandomPositiveBigDecimal(),
                    transactionDate = transactionDate,
                    billingAccount = BillingAccountSubject.createUserBillingAccount(userId = userId),
                    today = today,
                )
            }

            // then
            assertThrows<IllegalArgumentException> { action() }
        }
    }
}
