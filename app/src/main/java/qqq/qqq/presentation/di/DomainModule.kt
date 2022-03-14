package qqq.qqq.presentation.di

import org.koin.dsl.module
import qqq.qqq.domain.usecase.GetDataUserUseCase
import qqq.qqq.domain.usecase.SaveDataUserUseCase

var domainModule = module {

    factory {
        GetDataUserUseCase(userRepository = get())
    }

    factory {
        SaveDataUserUseCase(userRepository = get())
    }
}