package deferred.payment.platform.util

import java.math.BigDecimal
import java.util.UUID

object Random {
    private val HOSTS = listOf("naver.com", "nate.com", "gmail.com", "test.me", "kakao.com", "yahoo.co.kr")
    private val NAMES = listOf("Bob", "Sam", "James", "John")

    fun createRandomPositiveLong(): Long {
        return (1..Long.MAX_VALUE).random()
    }

    fun createRandomDigits(length: Int): Int {
        if (length > 10) {
            throw IllegalArgumentException()
        }
        val first = (1..9).random()
        val rest =
            (2..length).map {
                (0..9).random()
            }
        return Integer.parseInt((listOf(first) + rest).joinToString(""))
    }

    fun createRandomPositiveBigDecimal(): BigDecimal {
        return BigDecimal.valueOf(createRandomPositiveLong())
    }

    fun createRandomCarNumber(): String {
        val ints =
            (1..4).map {
                createRandomDigits(4)
            }
        return ints.joinToString(separator = "-")
    }

    fun createRandomPhoneNumber(): String {
        val phoneNumbers =
            (1..2).map {
                createRandomDigits(4)
            }
        return "010${phoneNumbers.first()}${phoneNumbers.last()}"
    }

    fun createRandomEmail(): String {
        return "test${createRandomPositiveLong()}@${HOSTS.random()}"
    }

    fun createRandomName(): String {
        return NAMES.random()
    }

    fun createRandomString(): String {
        return UUID.randomUUID().toString()
    }
}
