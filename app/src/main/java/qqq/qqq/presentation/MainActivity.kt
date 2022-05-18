package qqq.qqq.presentation

import android.content.Context
import android.graphics.Color
import android.os.Bundle
import android.util.Log
import android.view.inputmethod.InputMethodManager
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.LinearLayoutManager
import io.reactivex.Observable
import io.reactivex.schedulers.Schedulers
import qqq.qqq.api.MyApi
import qqq.qqq.databinding.ActivityMainBinding
import qqq.qqq.presentation.adapter.DiffUtilCallback
import qqq.qqq.presentation.adapter.MyRecycleAdapter
import qqq.qqq.presentation.viewmodel.MainViewModel
import qqq.qqq.presentation.viewmodel.MainViewModelFactory


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
        var apiService = MyApi()

        lifecycleScope.launchWhenResumed {
            Log.e(
                "MyApi-launchWhenResumed",
                apiService.getPassengersData(page = 0, size = 1).toString()
            )
        }

        apiService.getPassengersRx(page = 0, size = 1)
            .subscribeOn(Schedulers.io())
            .doOnNext {
                Log.e("MyApi-Rx", it.toString())
            }
            .subscribe()

//        startActivity(Intent(this, SecondActivity::class.java))

        /**RecycleView*/
        adapter.setData(arrayData = arrayData)
        with(binding) {
            recycleView.layoutManager = LinearLayoutManager(applicationContext)
            recycleView.adapter = adapter
        }

        /**LiveData*/
        vm.resultLive.observe(this) {
            binding.tvData.text = it
        }

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

        binding.FocusContainer.setOnClickListener {
            hideKeyboard()
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

fun MainActivity.hideKeyboard() {
    (getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager).also {
        it.hideSoftInputFromWindow(
            currentFocus?.windowToken,
            0
        )
    }
    currentFocus?.clearFocus()
}


/**LiveSharedFlow*/
//vm.resultSharedFlow.onEach {
//    binding.tvData.text = it
//}.launchWhenStarted(lifecycleScope)

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