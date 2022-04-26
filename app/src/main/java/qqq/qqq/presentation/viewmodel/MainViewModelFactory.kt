package qqq.qqq.presentation.viewmodel

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import qqq.qqq.data.repository.UserRepositoryImpl
import qqq.qqq.data.storage.sharedpref.SharedPrefUserStorage
import qqq.qqq.domain.usecase.GetDataUserUseCase
import qqq.qqq.domain.usecase.SaveDataUserUseCase


class MainViewModelFactory(context: Context) : ViewModelProvider.Factory {

    private val sharedPrefUserStorage by lazy { SharedPrefUserStorage(context = context) }
    private val userRepositoryImpl by lazy { UserRepositoryImpl(userStorage = sharedPrefUserStorage) }
    private val getDataUserUseCase by lazy { GetDataUserUseCase(userRepository = userRepositoryImpl) }
    private val saveDataUserUseCase by lazy { SaveDataUserUseCase(userRepository = userRepositoryImpl) }

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return MainViewModel(
            getDataUserUseCase = getDataUserUseCase,
            saveDataUserUseCase = saveDataUserUseCase
        ) as T
    }
}