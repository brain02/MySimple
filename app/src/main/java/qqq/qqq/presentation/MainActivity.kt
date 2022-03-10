package qqq.qqq.presentation

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import qqq.qqq.data.repository.UserRepositoryImpl
import qqq.qqq.data.storage.SharedPrefUserStorage
import qqq.qqq.databinding.ActivityMainBinding
import qqq.qqq.domain.modal.UserData
import qqq.qqq.domain.usecase.GetDataUserUseCase
import qqq.qqq.domain.usecase.SaveDataUserUseCase

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    private val sharedPrefUserStorage by lazy { SharedPrefUserStorage(context = applicationContext) }
    private val userRepositoryImpl by lazy { UserRepositoryImpl(userStorage = sharedPrefUserStorage) }
    private val getDataUserUseCase by lazy { GetDataUserUseCase(userRepository = userRepositoryImpl) }
    private val saveDataUserUseCase by lazy { SaveDataUserUseCase(userRepository = userRepositoryImpl) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }

    override fun onResume() {
        super.onResume()

        binding.btnGetData.setOnClickListener {
            val user = getDataUserUseCase.execute()
            binding.tvData.text = "${user.firstName} ${user.lastName}"
        }

        binding.btnSetData.setOnClickListener {
            val inputName = binding.editInputData.text.toString()
            val saveUser =
                saveDataUserUseCase.execute(UserData(firstName = inputName, lastName = "Коваленко"))
            binding.tvData.text = "$saveUser"
        }
    }
}