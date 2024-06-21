package deferred.payment.platform.user.controller

import deferred.payment.platform.user.application.UserRegisterService
import deferred.payment.platform.user.dto.UserRegisterRequest
import deferred.payment.platform.user.dto.UserRegisterResponse
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/v1/users")
class UserController(
    private val userRegisterService: UserRegisterService
) {

    @PostMapping
    fun registerUser(@RequestBody request: UserRegisterRequest): UserRegisterResponse {
        return userRegisterService.register(request)
    }

}
