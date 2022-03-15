package qqq.qqq.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import qqq.qqq.domain.repository.UserRepository
import qqq.qqq.domain.usecase.GetDataUserUseCase
import qqq.qqq.domain.usecase.SaveDataUserUseCase

@Module
@InstallIn(ViewModelComponent::class)
class DomainModule {

    @Provides
    fun provideGetDataUserUseCase(userRepository: UserRepository): GetDataUserUseCase {
        return GetDataUserUseCase(userRepository = userRepository)
    }

    @Provides
    fun provideSaveDataUserUseCase(userRepository: UserRepository): SaveDataUserUseCase {
        return SaveDataUserUseCase(userRepository = userRepository)
    }

}