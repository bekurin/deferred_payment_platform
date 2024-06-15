package deferred.payment.platform

import jakarta.persistence.Column
import jakarta.persistence.Embeddable

@Embeddable
class AccountHolder(
    userId: Long?,
    corporateUserId: Long?,
) {
    init {
        if (userId == null && corporateUserId == null) {
            throw IllegalArgumentException()
        }
        if (userId != null && corporateUserId != null) {
            throw IllegalArgumentException()
        }
    }

    @Column(name = "user_id")
    var userId: Long? = userId
        protected set

    @Column(name = "corporate_user_id")
    var corporateUserId: Long? = corporateUserId
        protected set
}
