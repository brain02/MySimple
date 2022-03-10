package qqq.qqq.data.storage


interface UserStorage {

    fun save(userData: User): Boolean

    fun get(): User
}