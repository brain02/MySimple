package qqq.qqq.presentation

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import qqq.qqq.domain.models.UserData
import qqq.qqq.domain.usecase.GetDataUserUseCase
import qqq.qqq.domain.usecase.SaveDataUserUseCase

class MainViewModel(
    private val getDataUserUseCase: GetDataUserUseCase,
    private val saveDataUserUseCase: SaveDataUserUseCase
) : ViewModel() {

    private val resultLiveMutable = MutableLiveData<String>()
    val resultLive: LiveData<String> = resultLiveMutable

    fun save(text: String) {
        val result = saveDataUserUseCase.execute(UserData(firstName = text, lastName = "Коваленко"))
        resultLiveMutable.value = result.toString()
    }

    fun get() {
        val user = getDataUserUseCase.execute()
        resultLiveMutable.value = "${user.firstName} ${user.lastName}"
    }

}