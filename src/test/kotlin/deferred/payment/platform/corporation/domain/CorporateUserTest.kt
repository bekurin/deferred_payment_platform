package deferred.payment.platform.corporation.domain

import deferred.payment.platform.util.CorporateUserSubject
import deferred.payment.platform.util.CorporationSubject
import deferred.payment.platform.util.Random
import java.math.BigDecimal
import kotlin.test.Test
import org.junit.jupiter.api.Nested
import org.junit.jupiter.api.assertDoesNotThrow
import org.junit.jupiter.api.assertThrows

class CorporateUserTest {
    @Nested
    inner class `기업 회원을 생성할 때` {
        @Test
        fun `기업의 신용 한도가 초과되면 생성에 실패한다`() {
            // given
            val corporationCreditLimit = Random.createRandomPositiveBigDecimal()
            val corporation = CorporationSubject.create(
                creditLimit = corporationCreditLimit,
            )
            val corporateUser = CorporateUserSubject.create(
                corporation = corporation,
                creditLimit = corporationCreditLimit.plus(Random.createRandomPositiveBigDecimal()),
            )

            // when
            val action = { corporation.registerCorporateUser(corporateUser) }

            // then
            assertThrows<IllegalArgumentException> { action() }
        }

        @Test
        fun `기업의 신용 한도를 초과하지 않는다면 생성에 성공한다`() {
            // given
            val corporationCreditLimit = Random.createRandomPositiveBigDecimal()
            val corporation = CorporationSubject.create(
                creditLimit = corporationCreditLimit,
            )
            val corporateUser = CorporateUserSubject.create(
                corporation = corporation,
                creditLimit = corporationCreditLimit.minus(BigDecimal.ONE),
            )

            // when
            val action = { corporation.registerCorporateUser(corporateUser) }

            // then
            assertDoesNotThrow(action)
        }
    }
}
