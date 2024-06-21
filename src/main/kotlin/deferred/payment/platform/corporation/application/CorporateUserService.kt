package deferred.payment.platform.corporation.application

import deferred.payment.platform.corporation.dto.CorporateUserCreateRequest
import deferred.payment.platform.corporation.dto.CorporateUserResponse
import deferred.payment.platform.corporation.infrastructure.CorporateUserRepository
import deferred.payment.platform.corporation.infrastructure.CorporationRepository
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class CorporateUserService(
    private val corporationRepository: CorporationRepository,
    private val corporateUserRepository: CorporateUserRepository
) {
    @Transactional
    fun registerCorporateUser(request: CorporateUserCreateRequest): CorporateUserResponse {
        val corporation = corporationRepository.findById(request.corporationId)
            .orElseThrow { IllegalStateException() }
        val corporateUserInfo = request.toCorporateUserInfo()
        val validCorporateUser = corporation.registerCorporateUser(corporateUserInfo)
        val savedCorporateUser = corporateUserRepository.save(validCorporateUser)
        return CorporateUserResponse(savedCorporateUser)
    }
}
