package qqq.qqq.domain.usecase

import qqq.qqq.domain.modal.UserData
import qqq.qqq.domain.repository.UserRepository

class SaveDataUserUseCase(private val userRepository: UserRepository) {

    fun execute(userData: UserData): Boolean {
        val oldUserName = userRepository.getUser()
        if (oldUserName.firstName == userData.firstName) {
            return false
        }

        val result = userRepository.saveUser(userData = userData)
        return result
    }
}