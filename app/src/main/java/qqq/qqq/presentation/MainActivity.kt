package qqq.qqq.presentation

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import qqq.qqq.data.repository.UserRepositoryImpl
import qqq.qqq.data.storage.sharedpref.SharedPrefUserStorage
import qqq.qqq.databinding.ActivityMainBinding
import qqq.qqq.domain.models.UserData
import qqq.qqq.domain.usecase.GetDataUserUseCase
import qqq.qqq.domain.usecase.SaveDataUserUseCase

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var vm: MainViewModel


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        vm = ViewModelProvider(this, MainViewModelFactory(this))
            .get(MainViewModel::class.java)
    }

    override fun onResume() {
        super.onResume()

        vm.resultLive.observe(this, {
            binding.tvData.text = it
        })

        binding.btnGetData.setOnClickListener {
            vm.get()
        }

        binding.btnSetData.setOnClickListener {
            val inputName = binding.editInputData.text.toString()
            vm.save(text = inputName)
        }
    }
}