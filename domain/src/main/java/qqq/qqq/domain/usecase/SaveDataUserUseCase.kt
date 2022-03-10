package qqq.qqq.domain.usecase

import qqq.qqq.domain.models.UserData
import qqq.qqq.domain.repository.UserRepository

class SaveDataUserUseCase(private val userRepository: UserRepository) {

    fun execute(userData: UserData): Boolean {

        if (userData.firstName.isEmpty()) {
            return false
        }

        val result = userRepository.saveUser(userData = userData)
        return result
    }
}