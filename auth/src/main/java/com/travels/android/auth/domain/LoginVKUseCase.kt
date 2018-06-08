package com.travels.android.auth.domain


import com.travels.android.base.domain.UseCase
import com.travels.android.base.persistence.user.User
import com.travels.android.base.persistence.user.UserRepository
import io.reactivex.Single

class LoginVKUseCase(
        private val userRepository: UserRepository,
        private val authRepository: AuthRepository,
        private val accountManager: AccountManager
): UseCase<LoginVKParams, LoginVKResult> {
    override fun execute(param: LoginVKParams): Single<LoginVKResult> {
        return authRepository.loginVK(param.code)
                .flatMap { info -> userRepository.saveUser(info.user) }
                .flatMap { userId -> userRepository.getUser(userId) }
                .flatMap { user -> accountManager.registerUser(user) }
                .map { registerUserResult -> LoginVKResult(registerUserResult.user, registerUserResult.accountType) }
    }

}

data class LoginVKParams(val code: String)

data class LoginVKResult(val user: User, val accountType: String)