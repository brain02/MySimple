package qqq.qqq.presentation

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

    private val _resultSharedFlow =
        MutableSharedFlow<String>(
            replay = 1,
            extraBufferCapacity = 0,
            onBufferOverflow = BufferOverflow.DROP_OLDEST
        )
    val resultSharedFlow: SharedFlow<String> = _resultSharedFlow.asSharedFlow()

    private val _resultStateFlow = MutableStateFlow<String>("no data flow")
    val resultStateFlow: StateFlow<String> = _resultStateFlow.asStateFlow()

    private val _resultLive = MutableLiveData<String>()
    val resultLive: LiveData<String> = _resultLive

    private val queryDB = App.getDatabase()?.dao()

    fun save(text: String) {
        val result = saveDataUserUseCase.execute(UserData(firstName = text, lastName = "Коваленко"))
        _resultLive.value = result.toString()
        _resultStateFlow.value = result.toString()

        /**    Сохраняем в SQLite БД
         * queryDB?.saveUserDB(UserNameEntity(firstName = text, lastName = "Abramov"))
         */
    }

    fun get() {
        val user = getDataUserUseCase.execute()
        _resultLive.value = "${user.firstName} ${user.lastName}"
        _resultStateFlow.value = "${user.firstName} ${user.lastName}"
        _resultSharedFlow.tryEmit("${user.firstName} ${user.lastName}")
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