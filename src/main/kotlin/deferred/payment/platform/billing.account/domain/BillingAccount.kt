package deferred.payment.platform.billing.account.domain

import deferred.payment.platform.BaseEntity
import jakarta.persistence.Column
import jakarta.persistence.Embedded
import jakarta.persistence.Entity
import jakarta.persistence.Table
import java.time.LocalDate

@Entity
@Table(name = "billing_account")
class BillingAccount(
    billingAccountHolder: BillingAccountHolder,
    billingAddress: String,
    cardNumber: String,
    cardExpiryDate: LocalDate,
    cardCsv: String,
) : BaseEntity() {
    @Embedded
    var billingAccountHolder: BillingAccountHolder = billingAccountHolder
        protected set

    @Column(name = "billing_address")
    var billingAddress: String = billingAddress
        protected set

    @Column(name = "card_number")
    var cardNumber: String = cardNumber
        protected set

    @Column(name = "card_expiry_date")
    var cardExpiryDate: LocalDate = cardExpiryDate
        protected set

    @Column(name = "card_cvv")
    var cardCsv: String = cardCsv
        protected set
}
