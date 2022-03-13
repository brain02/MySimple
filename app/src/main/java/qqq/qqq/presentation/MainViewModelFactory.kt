package qqq.qqq.presentation

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import qqq.qqq.data.repository.UserRepositoryImpl
import qqq.qqq.data.storage.sharedpref.SharedPrefUserStorage
import qqq.qqq.domain.usecase.GetDataUserUseCase
import qqq.qqq.domain.usecase.SaveDataUserUseCase


class MainViewModelFactory(
    val getDataUserUseCase: GetDataUserUseCase,
    val saveDataUserUseCase: SaveDataUserUseCase
) : ViewModelProvider.Factory {


    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return MainViewModel(
            getDataUserUseCase = getDataUserUseCase,
            saveDataUserUseCase = saveDataUserUseCase
        ) as T
    }
}