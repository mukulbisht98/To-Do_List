package com.xxmukulxx.to_dolist.common.data.data_store.vm

import androidx.lifecycle.viewModelScope
import com.xxmukulxx.to_dolist.common.base.BaseViewModel
import com.xxmukulxx.to_dolist.common.data.data_store.DataStore
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DataStoreViewModel @Inject constructor(private val datastore: DataStore) :
    BaseViewModel() {

    val readFromLocal = datastore.readFromLocal

    fun clearData() = viewModelScope.launch {
        datastore.clearData()
    }

    fun saveToLocal(modeType: Int) = viewModelScope.launch {
        datastore.saveToLocal(modeType)
    }
}