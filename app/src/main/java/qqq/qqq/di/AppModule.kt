package qqq.qqq.di

import android.content.Context
import dagger.Module
import dagger.Provides
import qqq.qqq.domain.usecase.GetDataUserUseCase
import qqq.qqq.domain.usecase.SaveDataUserUseCase
import qqq.qqq.presentation.MainViewModelFactory

@Module
class AppModule(val context: Context) {
    @Provides
    fun provideContext(): Context {
        return context
    }

    @Provides
    fun provideMainViewModelFactory(
        getDataUserUseCase: GetDataUserUseCase,
        saveDataUserUseCase: SaveDataUserUseCase
    ): MainViewModelFactory {
        return MainViewModelFactory(
            getDataUserUseCase = getDataUserUseCase,
            saveDataUserUseCase = saveDataUserUseCase
        )
    }

}