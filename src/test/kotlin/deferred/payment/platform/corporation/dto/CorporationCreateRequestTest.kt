package deferred.payment.platform.corporation.dto

import deferred.payment.platform.util.Random
import java.math.BigDecimal
import org.assertj.core.api.SoftAssertions
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Nested
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.ValueSource

class CorporationCreateRequestTest {

    @Nested
    inner class `기업 생성 request를 생성할 때` {

        @ParameterizedTest
        @ValueSource(strings = ["   ", ""])
        fun `이름이 공백이거나 빈 문자일 수 없다`(name: String) {
            // given
            val validCreditLimit = Random.createRandomPositiveBigDecimal()

            // when
            val action = { CorporationCreateRequest(name, creditLimit = validCreditLimit) }

            // then
            assertThrows<IllegalArgumentException> { action() }
        }

        @ParameterizedTest
        @ValueSource(longs = [0, -1, -2, -3])
        fun `신용 한도가 0 보다 크거나 같을 수 없다`(creditLimit: Long) {
            // given
            val validName = Random.createRandomName()

            // when
            val action = { CorporationCreateRequest(validName, creditLimit = BigDecimal.valueOf(creditLimit)) }

            // then
            assertThrows<IllegalArgumentException> { action() }
        }

        @DisplayName(
            """
            1. 이름이 공백이거나 빈 문자이지 않아야한다
            2. 신용 한도가 1보다 크거나 같아야한다
        """
        )
        @Test
        fun `유효한 조건이면 생성에 성공한다`() {
            // given
            val (creditLimit, name) = Pair(Random.createRandomPositiveBigDecimal(), Random.createRandomName())

            // when
            val createdRequest = CorporationCreateRequest(name = name, creditLimit = creditLimit)

            // then
            SoftAssertions.assertSoftly { softly ->
                softly.assertThat(createdRequest.name).isEqualTo(name)
                softly.assertThat(createdRequest.creditLimit).isEqualTo(creditLimit)
            }
        }
    }
}
