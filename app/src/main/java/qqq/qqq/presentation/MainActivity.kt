package qqq.qqq.presentation

import android.graphics.Color
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.onEach
import qqq.qqq.presentation.adapter.MyRecycleAdapter
import qqq.qqq.databinding.ActivityMainBinding
import qqq.qqq.launchWhenStarted

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var vm: MainViewModel
    private var arrayData = arrayListOf("lol", "fact", "ivan", "best", "check", "adapter")


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        vm = ViewModelProvider(this, MainViewModelFactory(this))
            .get(MainViewModel::class.java)
    }

    override fun onResume() {
        super.onResume()

        /**RecycleView*/
        val adapter = MyRecycleAdapter()
        adapter.setData(arrayData = arrayData)
        with(binding) {
            recycleView.layoutManager = LinearLayoutManager(applicationContext)
            recycleView.adapter = adapter
        }

        /**LiveData*/
//        vm.resultLive.observe(this, {
//            binding.tvData.text = it
//        })

        /**LiveStateFlow*/
//        vm.resultStateFlow.onEach {
//            binding.tvData.text = it
//        }.launchWhenStarted(lifecycleScope)

        /**LiveSharedFlow*/
        vm.resultSharedFlow.onEach {
            binding.tvData.text = it
        }.launchWhenStarted(lifecycleScope)

        binding.btnGetData.setOnClickListener {
            vm.get()
        }

        binding.btnSetData.setOnClickListener {
            val inputName = binding.editInputData.text.toString()
            vm.save(text = inputName)
            arrayData.add(inputName)
            binding.recycleView.adapter?.notifyDataSetChanged()
        }

        binding.btnSwapColor.setOnClickListener {
            binding.customView.swapColor()
            binding.customView.foregroundColor(color = Color.GRAY)
        }
    }
}