package qqq.qqq.di

import android.content.Context
import dagger.Module
import dagger.Provides
import qqq.qqq.data.repository.UserRepositoryImpl
import qqq.qqq.data.storage.UserStorage
import qqq.qqq.data.storage.sharedpref.SharedPrefUserStorage
import qqq.qqq.domain.repository.UserRepository

@Module
class DataModule {

    @Provides
    fun providerSharedPrefUserStorage(context: Context): UserStorage{
        return SharedPrefUserStorage(context = context)
    }

    @Provides
    fun providerUserRepositoryImpl(userStorage:UserStorage): UserRepository{
        return UserRepositoryImpl(userStorage = userStorage)
    }
}