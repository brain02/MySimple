package qqq.qqq.data.storage

import qqq.qqq.data.storage.models.User


interface UserStorage {

    fun save(userData: User): Boolean

    fun get(): User
}