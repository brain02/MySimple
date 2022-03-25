package qqq.qqq.presentation

import android.graphics.Color
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import io.reactivex.Observable
import io.reactivex.rxkotlin.subscribeBy
import io.reactivex.rxkotlin.toObservable
import kotlinx.coroutines.flow.onEach
import qqq.qqq.databinding.ActivityMainBinding
import qqq.qqq.launchWhenStarted
import qqq.qqq.presentation.adapter.MyRecycleAdapter

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

        binding.btnRxJava.setOnClickListener {
//            SampleRxJava(context = applicationContext).startRStream()
            startRStream()
        }
    }

    private fun startRStream() {

        val numbers = Observable.range(1, 6)

        val strings = Observable.just("One", "Two", "Three","Five", "Six" )

        val list = listOf("1", "2", "3", "4", "5")
        list.toObservable()
            .subscribeBy(
                onNext = { println(it) },
                onError = { it.printStackTrace() },
                onComplete = { println("onComplete!") }
            )
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