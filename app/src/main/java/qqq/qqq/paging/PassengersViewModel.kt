package qqq.qqq.paging

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.cachedIn
import qqq.qqq.api.MyApi

class PassengersViewModel(
    private val api: MyApi
) : ViewModel() {
    val passengers =
        Pager(config = PagingConfig(pageSize = 10), pagingSourceFactory = {
            PassengersDataSource(api)
        }).flow.cachedIn(viewModelScope)
}