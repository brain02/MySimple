package qqq.qqq.domain.usecase

import qqq.qqq.domain.models.UserData
import qqq.qqq.domain.repository.UserRepository

class SaveDataUserUseCase(private val userRepository: UserRepository) {

    fun execute(userData: UserData): Boolean {

        val oldUserData = userRepository.getUser()

        if (oldUserData.firstName == userData.firstName) {
            return true
        }

        val result = userRepository.saveUser(userData = userData)
        return result
    }
}