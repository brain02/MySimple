package qqq.qqq.data.repository

import qqq.qqq.data.storage.models.User
import qqq.qqq.data.storage.UserStorage
import qqq.qqq.domain.models.UserData
import qqq.qqq.domain.repository.UserRepository


class UserRepositoryImpl(private val userStorage: UserStorage) : UserRepository {

    override fun getUser(): UserData {
        return mapToDomain(user = userStorage.get())
    }

    override fun saveUser(userData: UserData): Boolean {
        val user = mapToStorage(userData = userData)
        return userStorage.save(user)
    }

    private fun mapToStorage(userData: UserData): User {
        return User(firstName = userData.firstName, lastName = userData.lastName)
    }

    private fun mapToDomain(user: User): UserData {
        return UserData(firstName = user.firstName, lastName = user.lastName)
    }

}