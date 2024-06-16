package deferred.payment.platform.corporation.application

import deferred.payment.platform.corporation.domain.CorporationRepository
import deferred.payment.platform.corporation.dto.CorporationCreateRequest
import deferred.payment.platform.corporation.dto.CorporationResponse
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
@Transactional(readOnly = true)
class CorporationService(
    private val corporationRepository: CorporationRepository
) {
    @Transactional
    fun create(request: CorporationCreateRequest): CorporationResponse {
        val corporation = request.toCorporation()
        val savedCorporation = corporationRepository.save(corporation)
        return CorporationResponse(savedCorporation)
    }
}
