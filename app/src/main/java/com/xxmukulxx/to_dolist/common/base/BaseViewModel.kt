package com.xxmukulxx.to_dolist.common.base

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.xxmukulxx.to_dolist.MyApplication
import com.xxmukulxx.to_dolist.R

abstract class BaseViewModel() : ViewModel() {
    private val isLoading = MutableLiveData<Boolean>()
    private val displayError = MutableLiveData<String>()

    fun getIsLoadingLiveData(): LiveData<Boolean> {
        return isLoading
    }

    fun getDisplayErrorLiveData(): LiveData<String> {
        return displayError
    }

    protected fun handleNetworkError() {
        isLoading.postValue(false)
        displayError.postValue(MyApplication.appContext.resources.getString(R.string.network_error))
    }

    protected fun handleUnauthorizedError() {
        isLoading.postValue(false)
        //Pending
    }
}