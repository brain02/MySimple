package qqq.qqq.presentation

import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.coroutines.flow.onEach
import qqq.qqq.api.MyApi
import qqq.qqq.databinding.ActivityMainBinding
import qqq.qqq.launchWhenStarted
import qqq.qqq.presentation.adapter.DiffUtilCallback
import qqq.qqq.presentation.adapter.MyRecycleAdapter
import qqq.qqq.presentation.viewmodel.MainViewModel
import qqq.qqq.presentation.viewmodel.MainViewModelFactory
import qqq.qqq.presentation.viewmodel.SecondActivity


class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var vm: MainViewModel
    private val adapter = MyRecycleAdapter()
    private var arrayData: ArrayList<String> =
        arrayListOf("lol", "fact", "ivan", "best", "check", "adapter")
    private var arrayData2 = arrayListOf("111", "fact", "333", "best", "check", "666")

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        vm = ViewModelProvider(this, MainViewModelFactory(this))
            .get(MainViewModel::class.java)
    }

    override fun onResume() {
        super.onResume()

        startActivity(Intent(this, SecondActivity::class.java))
        lifecycleScope.launchWhenResumed {
            Log.e("MyApi", MyApi().getPassengersData(page = 0, size = 1).toString())
        }

        /**RecycleView*/
        adapter.setData(arrayData = arrayData)
        with(binding) {
            recycleView.layoutManager = LinearLayoutManager(applicationContext)
            recycleView.adapter = adapter
        }

        /**LiveSharedFlow*/
        vm.resultSharedFlow.onEach {
            binding.tvData.text = it
        }.launchWhenStarted(lifecycleScope)

        binding.btnGetData.setOnClickListener {
            vm.get()
            updateRecycleView()
        }

        binding.btnSetData.setOnClickListener {
            val inputName = binding.editInputData.text.toString()
            vm.save(text = inputName)
        }

        binding.btnSwapColor.setOnClickListener {
            binding.customView.swapColor()
            binding.customView.foregroundColor(color = Color.GRAY)
        }

        binding.btnRxJava.setOnClickListener {
            SampleRxJava(context = this).startRStream()
        }
    }

    private fun updateRecycleView() {
        val diffUtilCallback =
            DiffUtilCallback(oldList = adapter.getData(), newList = arrayData2)
        val diffResult = DiffUtil.calculateDiff(diffUtilCallback, false)
        adapter.setData(arrayData = arrayData2)
        diffResult.dispatchUpdatesTo(adapter)
    }
}

/**LiveData*/
//        vm.resultLive.observe(this, {
//            binding.tvData.text = it
//        })

/**LiveStateFlow*/
//        vm.resultStateFlow.onEach {
//            binding.tvData.text = it
//        }.launchWhenStarted(lifecycleScope)

/**RxKotlin*/
//    private fun startRStream() {
//        var list = listOf("1", "2", "3", "4", "5")
//        var sett = setOf<String>("")
//        sett
//        list.toObservable()
//            .subscribeBy(
//                onNext = { println(it) },
//                onError = { it.printStackTrace() },
//                onComplete = { println("onComplete!") }
//            )
//    }