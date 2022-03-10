package qqq.qqq.domain.usecase

import qqq.qqq.domain.modal.UserData
import qqq.qqq.domain.repository.UserRepository

class GetDataUserUseCase(private val userRepository: UserRepository) {

    fun execute():UserData {
        val result = userRepository.getUser()
        return  result
    }
}