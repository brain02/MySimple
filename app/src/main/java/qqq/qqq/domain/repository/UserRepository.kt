package qqq.qqq.domain.repository

import qqq.qqq.domain.modal.UserData


interface UserRepository {

    fun getUser(): UserData
    fun saveUser(userData: UserData): Boolean
}