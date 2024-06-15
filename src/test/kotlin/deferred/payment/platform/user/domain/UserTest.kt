package deferred.payment.platform.user.domain

import deferred.payment.platform.util.Random
import deferred.payment.platform.util.TransactionSubject
import deferred.payment.platform.util.UserSubject
import java.math.BigDecimal
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertDoesNotThrow
import org.junit.jupiter.api.assertThrows

class UserTest {
    @Test
    fun `개인 회원은 미납금액이 0원인 채로 생성된다`() {
        // given
        // when
        val user = User(
            name = Random.createRandomName(),
            email = Random.createRandomEmail(),
            phoneNumber = Random.createRandomPhoneNumber(),
            creditLimit = Random.createRandomPositiveBigDecimal(),
        )

        // then
        assertThat(user.outstandingBalance).isEqualTo(BigDecimal.ZERO)
    }

    @Test
    fun `일반 사용자의 미납 금액과 현재 결제 예정에 있는 금액이 신용 한도를 넘으면 예외가 발생한다`() {
        // given
        val (creditLimit, outstandingBalance) = Pair(BigDecimal.valueOf(10L), BigDecimal.ZERO)
        val user = UserSubject.create(
            creditLimit = creditLimit,
            outstandingBalance = outstandingBalance,
        )
        val transactions = (1..5L).map { amount ->
            TransactionSubject.createUserTransaction(userId = user.id, amount = BigDecimal.valueOf(amount))
        }

        // when
        val action = { user.validateCreditLimitExceeded(transactions) }

        // then
        assertThrows<IllegalArgumentException> { action() }
    }

    @Test
    fun `일반 사용자의 미납 금액과 현재 결제 예정에 있는 금액이 신용 한도를 넘지 않아야한다`() {
        // given
        val (creditLimit, outstandingBalance) = Pair(BigDecimal.valueOf(10L), BigDecimal.ZERO)
        val user = UserSubject.create(
            creditLimit = creditLimit,
            outstandingBalance = outstandingBalance,
        )
        val transactions = (1..5).map {
            TransactionSubject.createUserTransaction(userId = user.id, amount = BigDecimal.ONE)
        }

        // when
        val action = { user.validateCreditLimitExceeded(transactions) }

        // then
        assertDoesNotThrow(action)
    }
}
