package qqq.qqq.presentation.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.*
import kotlinx.coroutines.channels.BufferOverflow
import kotlinx.coroutines.flow.*
import qqq.qqq.app.App
import qqq.qqq.domain.models.UserData
import qqq.qqq.domain.usecase.GetDataUserUseCase
import qqq.qqq.domain.usecase.SaveDataUserUseCase

class MainViewModel(
    private val getDataUserUseCase: GetDataUserUseCase,
    private val saveDataUserUseCase: SaveDataUserUseCase
) : ViewModel() {

    /**SharedFlow*/
    private val _resultSharedFlow =
        MutableSharedFlow<String>(
            replay = 1,
            extraBufferCapacity = 0,
            onBufferOverflow = BufferOverflow.DROP_OLDEST
        )
    val resultSharedFlow: SharedFlow<String> = _resultSharedFlow.asSharedFlow()

    /**StateFlow*/
    private val _resultStateFlow = MutableStateFlow<String>("no data flow")
    val resultStateFlow: StateFlow<String> = _resultStateFlow.asStateFlow()

    /**LiveData*/
    private val _resultLive = MutableLiveData<String>()
    val resultLive: LiveData<String> = _resultLive

    /**DB Room*/
//    private val queryDB = App.getDatabase()?.dao()

    /**Функция сохранить введенные данные*/
    fun save(text: String) {
        val result = saveDataUserUseCase.execute(UserData(firstName = text, lastName = "Коваленко"))
        _resultLive.value = result.toString()
        _resultStateFlow.value = result.toString()
        _resultSharedFlow.tryEmit(result.toString())
//        queryDB?.saveUserDB(UserNameEntity(firstName = text, lastName = "Abramov"))
    }

    /**Получить сохраненные данные*/
    fun get() {
        val user = getDataUserUseCase.execute()
        _resultLive.value = "${user.firstName} ${user.lastName}"
        _resultStateFlow.value = "${user.firstName} ${user.lastName}"
        _resultSharedFlow.tryEmit("${user.firstName} ${user.lastName}")
    }

    /**Инициализируем корутин во ViewModel*/
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