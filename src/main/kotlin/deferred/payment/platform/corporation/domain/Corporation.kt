package deferred.payment.platform.corporation.domain

import deferred.payment.platform.BaseEntity
import deferred.payment.platform.billing.account.domain.Transaction
import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.OneToMany
import jakarta.persistence.Table
import java.math.BigDecimal

@Entity
@Table(name = "corporation")
class Corporation(
    name: String,
    creditLimit: BigDecimal,
    outstandingBalance: BigDecimal = BigDecimal.ZERO,
) : BaseEntity() {
    @Column(name = "name", nullable = false)
    var name: String = name
        protected set

    @Column(name = "credit_limit", nullable = false)
    var creditLimit: BigDecimal = creditLimit
        protected set

    @Column(name = "outstanding_balance", nullable = false)
    var outstandingBalance: BigDecimal = outstandingBalance
        protected set

    @OneToMany(mappedBy = "corporation")
    var corporateUsers: MutableList<CorporateUser> = mutableListOf()
        protected set

    fun registerCorporateUser(corporateUserInfo: CorporateUserInfo): CorporateUser {
        if (canAddCorporateUser(corporateUserInfo).not()) {
            throw IllegalArgumentException()
        }
        return CorporateUser(
            name = corporateUserInfo.name,
            email = corporateUserInfo.email,
            phoneNumber = corporateUserInfo.phoneNumber,
            corporation = this,
            creditLimit = corporateUserInfo.creditLimit
        )
    }

    private fun canAddCorporateUser(corporateUserInfo: CorporateUserInfo): Boolean {
        val totalCreditLimit = corporateUsers.sumOf { it.creditLimit } + corporateUserInfo.creditLimit
        return totalCreditLimit <= creditLimit
    }

    fun validateCreditLimitExceeded(transactions: List<Transaction>) {
        val totalAmount = transactions.sumOf { it.amount }
        if (totalAmount + outstandingBalance > creditLimit) {
            throw IllegalArgumentException()
        }
    }
}
