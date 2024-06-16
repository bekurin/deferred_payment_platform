package deferred.payment.platform.corporation.controller

import deferred.payment.platform.corporation.application.CorporationService
import deferred.payment.platform.corporation.dto.CorporationCreateRequest
import deferred.payment.platform.corporation.dto.CorporationResponse
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/v1/corporations")
class CorporationController(
    private val corporationService: CorporationService
) {
    @PostMapping
    fun create(
        @RequestBody request: CorporationCreateRequest
    ): CorporationResponse {
        return corporationService.create(request)
    }
}
