package qqq.qqq.presentation.di

import org.koin.dsl.module
import qqq.qqq.data.repository.UserRepositoryImpl
import qqq.qqq.data.storage.UserStorage
import qqq.qqq.data.storage.sharedpref.SharedPrefUserStorage
import qqq.qqq.domain.repository.UserRepository

var dataModule = module {

    single<UserStorage> {
        SharedPrefUserStorage(context = get())
    }

    single<UserRepository> {
        UserRepositoryImpl(userStorage = get())
    }
}