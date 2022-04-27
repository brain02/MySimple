package qqq.qqq.paging

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import qqq.qqq.api.MyApi
import qqq.qqq.presentation.viewmodel.MainViewModel

class PassengersViewModelFactory(
    private val api: MyApi
) : ViewModelProvider.NewInstanceFactory() {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return PassengersViewModel(api) as T
    }
}