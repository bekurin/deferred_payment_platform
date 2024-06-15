package deferred.payment.platform.billing.account.domain

import deferred.payment.platform.AccountHolder
import deferred.payment.platform.util.Random
import org.junit.jupiter.api.Nested
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertDoesNotThrow
import org.junit.jupiter.api.assertThrows
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.CsvSource

class BillingAccountHolderTest {
    @Nested
    inner class `결제 수단 소유주를 생성할 때` {
        @Test
        fun `개인 회원과 기업이 둘 다 존재할 수 없다`() {
            // given
            val (individualUserId, corporationId) = Pair(
                Random.createRandomPositiveLong(),
                Random.createRandomPositiveLong()
            )

            // when
            val action = { AccountHolder(individualUserId, corporationId) }

            // then
            assertThrows<IllegalArgumentException> {
                action()
            }
        }

        @Test
        fun `개인 회원과 기업이 둘 다 없을 수 없다`() {
            // given
            val (individualUserId, corporationId) = Pair(null, null)

            // when
            val action = { AccountHolder(individualUserId, corporationId) }

            // then
            assertThrows<IllegalArgumentException> {
                action()
            }
        }

        @ParameterizedTest
        @CsvSource(
            value = [
                "100, null",
                "null, 100",
            ],
            nullValues = ["null"],
        )
        fun `둘 중 하나만 있다면 정상적으로 생성된다`(
            individualUserId: Long?,
            corporationId: Long?,
        ) {
            // given
            // when
            val action = { AccountHolder(individualUserId, corporationId) }

            // then
            assertDoesNotThrow(action)
        }
    }
}
