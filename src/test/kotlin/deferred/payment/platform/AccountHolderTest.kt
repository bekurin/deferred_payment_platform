package deferred.payment.platform

import deferred.payment.platform.util.Random
import org.junit.jupiter.api.Nested
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertDoesNotThrow
import org.junit.jupiter.api.assertThrows
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.CsvSource

class AccountHolderTest {
    @Nested
    inner class `계정 소유자를 생성할 때` {
        @Test
        fun `개인 회원과 기업 회원이 둘 다 존재할 수 없다`() {
            // given
            val (individualUserId, corporateUserId) = Pair(
                Random.createRandomPositiveLong(),
                Random.createRandomPositiveLong()
            )

            // when
            val action = { AccountHolder(individualUserId, corporateUserId) }

            // then
            assertThrows<IllegalArgumentException> {
                action()
            }
        }

        @Test
        fun `개인 회원과 기업 회원이 둘 다 없을 수 없다`() {
            // given
            val (individualUserId, corporateUserId) = Pair(null, null)

            // when
            val action = { AccountHolder(individualUserId, corporateUserId) }

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
        fun `둘 중 하나만 있다면 정상적으로 생성된다`(individualUserId: Long?, corporateUserId: Long?) {
            // given
            // when
            val action = { AccountHolder(individualUserId, corporateUserId) }

            // then
            assertDoesNotThrow(action)
        }
    }
}
