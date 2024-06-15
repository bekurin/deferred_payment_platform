package deferred.payment.platform.corporation.domain

import deferred.payment.platform.BaseEntity
import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.JoinColumn
import jakarta.persistence.ManyToOne
import jakarta.persistence.Table
import java.math.BigDecimal

@Entity
@Table(name = "corporate_user")
class CorporateUser(
    name: String,
    email: String,
    phoneNumber: String,
    corporation: Corporation,
    creditLimit: BigDecimal,
) : BaseEntity() {
    @Column(name = "name", nullable = false)
    var name: String = name
        protected set

    @Column(name = "email", nullable = false)
    var email: String = email
        protected set

    @Column(name = "phone_number", nullable = false)
    var phoneNumber: String = phoneNumber
        protected set

    @ManyToOne
    @JoinColumn(name = "corporation_id")
    var corporation: Corporation = corporation
        protected set

    @Column(name = "credit_limit", nullable = false)
    var creditLimit: BigDecimal = creditLimit
        protected set
}
