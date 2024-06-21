package deferred.payment.platform.user.application

import deferred.payment.platform.user.domain.UserRepository
import deferred.payment.platform.user.dto.UserRegisterRequest
import deferred.payment.platform.user.dto.UserRegisterResponse
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class UserRegisterService(
    private val userRepository: UserRepository
) {
    @Transactional
    fun register(request: UserRegisterRequest): UserRegisterResponse {
        val user = request.toUser()
        val savedUser = userRepository.save(user)
        return UserRegisterResponse(savedUser)
    }
}
