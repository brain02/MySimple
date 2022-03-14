package qqq.qqq.presentation.di

import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module
import qqq.qqq.domain.usecase.SaveDataUserUseCase
import qqq.qqq.presentation.MainViewModel

var appModule = module {
    viewModel {
        MainViewModel(
            getDataUserUseCase = get(),
            saveDataUserUseCase = get()
        )
    }
}
