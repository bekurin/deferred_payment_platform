package deferred.payment.platform.corporation.controller

import deferred.payment.platform.corporation.application.CorporateUserService
import deferred.payment.platform.corporation.dto.CorporateUserCreateRequest
import deferred.payment.platform.corporation.dto.CorporateUserResponse
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/v1/corporate-users")
class CorporateUserController(
    private val corporateUserService: CorporateUserService
) {

    @PostMapping
    fun registerCorporateUser(
        @RequestBody request: CorporateUserCreateRequest
    ): CorporateUserResponse {
        return corporateUserService.registerCorporateUser(request)
    }
}
