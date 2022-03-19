package qqq.qqq.presentation

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import kotlinx.coroutines.flow.onEach
import qqq.qqq.databinding.ActivityMainBinding
import qqq.qqq.launchWhenStarted

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
// LiveData
//        vm.resultLive.observe(this, {
//            binding.tvData.text = it
//        })

//        LiveStateFlow
//        vm.resultStateFlow.onEach {
//            binding.tvData.text = it
//        }.launchWhenStarted(lifecycleScope)


//        LiveSharedFlow
        vm.resultSharedFlow.onEach {
            binding.tvData.text = it
        }.launchWhenStarted(lifecycleScope)

        binding.btnGetData.setOnClickListener {
            vm.get()
        }

        binding.btnSetData.setOnClickListener {
            val inputName = binding.editInputData.text.toString()
            vm.save(text = inputName)
        }
    }
}