package qqq.qqq.presentation.viewmodel

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import qqq.qqq.api.MyApi
import qqq.qqq.databinding.ActivitySecondBinding
import qqq.qqq.paging.PassengersAdapter
import qqq.qqq.paging.PassengersLoadStateAdapter
import qqq.qqq.paging.PassengersViewModel
import qqq.qqq.paging.PassengersViewModelFactory

class SecondActivity : AppCompatActivity() {
    lateinit var passengersViewModel: PassengersViewModel
    lateinit var passengersAdapter: PassengersAdapter
    private lateinit var binding: ActivitySecondBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySecondBinding.inflate(layoutInflater)

        setContentView(binding.root)

        setupViewModel()
        setupView()
        setupList()
    }

    private fun setupViewModel() {
        val factory = PassengersViewModelFactory(MyApi())
        passengersViewModel = ViewModelProvider(this, factory).get(PassengersViewModel::class.java)
    }

    private fun setupView() {
        passengersAdapter = PassengersAdapter()
        passengersAdapter.withLoadStateHeaderAndFooter(
            header = PassengersLoadStateAdapter { passengersAdapter.retry() },
            footer = PassengersLoadStateAdapter { passengersAdapter.retry() }
        )
        binding.recyclerView.apply {
            layoutManager = LinearLayoutManager(context)
            adapter = passengersAdapter
            setHasFixedSize(true)
        }
    }

    private fun setupList() {
        lifecycleScope.launch {
            passengersViewModel.passengers.collectLatest { pagedData ->
                passengersAdapter.submitData(pagedData)
            }
        }
    }
}