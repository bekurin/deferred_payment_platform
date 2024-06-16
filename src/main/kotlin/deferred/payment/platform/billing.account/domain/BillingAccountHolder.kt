package deferred.payment.platform.billing.account.domain

import jakarta.persistence.Column
import jakarta.persistence.Embeddable

@Embeddable
class BillingAccountHolder(
    userId: Long?,
    corporationId: Long?,
) {
    init {
        if (userId == null && corporationId == null) {
            throw IllegalArgumentException()
        }
        if (userId != null && corporationId != null) {
            throw IllegalArgumentException()
        }
    }

    @Column(name = "user_id")
    var userId: Long? = userId
        protected set

    @Column(name = "corporation_id")
    var corporationId: Long? = corporationId
        protected set
}
