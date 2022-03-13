package qqq.qqq.presentation

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import qqq.qqq.app.App
import qqq.qqq.databinding.ActivityMainBinding
import javax.inject.Inject

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    @Inject
    lateinit var vmFactory: MainViewModelFactory
    private lateinit var vm: MainViewModel


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        (applicationContext as App).appCompanent.inject(this)

        vm = ViewModelProvider(this, vmFactory)
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