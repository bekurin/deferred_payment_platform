package deferred.payment.platform.corporation.domain

import deferred.payment.platform.util.CorporationSubject
import deferred.payment.platform.util.Random
import deferred.payment.platform.util.TransactionSubject
import java.math.BigDecimal
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertDoesNotThrow
import org.junit.jupiter.api.assertThrows

class CorporationTest {
    @Test
    fun `법인을 생성할 때 미납금액이 0원인 채로 생성된다`() {
        // given
        val expectedOutstandingBalance = BigDecimal.ZERO
        val (name, creditLimit) = Pair(Random.createRandomName(), Random.createRandomPositiveBigDecimal())

        // when
        val corporation = Corporation(name = name, creditLimit = creditLimit)

        // then
        assertThat(corporation.outstandingBalance).isEqualTo(expectedOutstandingBalance)
    }

    @Test
    fun `법인의 미납 금액과 현재 결제 예정에 있는 금액이 신용 한도를 넘으면 예외가 발생한다`() {
        // given
        val (creditLimit, outstandingBalance) = Pair(BigDecimal.valueOf(10L), BigDecimal.ZERO)
        val corporation = CorporationSubject.create(
            creditLimit = creditLimit,
            outstandingBalance = outstandingBalance,
        )
        val transactions = (1..5L).map { amount ->
            TransactionSubject.createCorporateUserTransaction(
                corporationId = corporation.id,
                amount = BigDecimal.valueOf(amount)
            )
        }

        // when
        val action = { corporation.validateCreditLimitExceeded(transactions) }

        // then
        assertThrows<IllegalArgumentException> { action() }
    }

    @Test
    fun `법인의 미납 금액과 현재 결제 예정에 있는 금액이 신용 한도를 넘지 않아야한다`() {
        // given
        val (creditLimit, outstandingBalance) = Pair(BigDecimal.valueOf(10L), BigDecimal.ZERO)
        val corporation = CorporationSubject.create(
            creditLimit = creditLimit,
            outstandingBalance = outstandingBalance,
        )
        val transactions = (1..5).map {
            TransactionSubject.createCorporateUserTransaction(corporationId = corporation.id, amount = BigDecimal.ONE)
        }

        // when
        val action = { corporation.validateCreditLimitExceeded(transactions) }

        // then
        assertDoesNotThrow(action)
    }
}
