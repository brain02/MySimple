package qqq.qqq.presentation

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.*
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import qqq.qqq.app.App
import qqq.qqq.data.storage.room.UserNameEntity
import qqq.qqq.domain.models.UserData
import qqq.qqq.domain.usecase.GetDataUserUseCase
import qqq.qqq.domain.usecase.SaveDataUserUseCase

class MainViewModel(
    private val getDataUserUseCase: GetDataUserUseCase,
    private val saveDataUserUseCase: SaveDataUserUseCase
) : ViewModel() {

    private val _resultStateFlow = MutableStateFlow<String>("no data flow")
    val resultStateFlow: StateFlow<String> = _resultStateFlow

    private val resultLiveMutable = MutableLiveData<String>()
    val resultLive: LiveData<String> = resultLiveMutable

    private val queryDB = App.getDatabase()?.dao()

    fun save(text: String) {
        queryDB?.saveUserDB(UserNameEntity(firstName =  text, lastName = "Abramov"))
        val result = saveDataUserUseCase.execute(UserData(firstName = text, lastName = "Коваленко"))
        resultLiveMutable.value = result.toString()
        _resultStateFlow.value = result.toString()
    }

    fun get() {
        val user = getDataUserUseCase.execute()
        resultLiveMutable.value = "${user.firstName} ${user.lastName}"
        _resultStateFlow.value = "${user.firstName} ${user.lastName}"
    }

    init {
        viewModelScope.launch {
            delay(1000)
            val result = withContext(Dispatchers.Default) {
                val part1 = async {
                    delay(1000)
                    return@async "Part 1"
                }
                val part2 = async {
                    delay(2000)
                    return@async "Part 2"
                }
                val part3 = async {
                    delay(3000)
                    return@async "Part 3"
                }

                val result1 = part1.await()
                val result2 = part2.await()
                val result3 = part3.await()
                return@withContext "$result1,$result2,$result3"
            }
            Log.e("withContext", result)
        }
    }
}