package com.xxmukulxx.to_dolist.common.presentation.activity

import com.xxmukulxx.to_dolist.common.base.BaseActivity
import com.xxmukulxx.to_dolist.common.data.data_store.vm.DataStoreViewModel
import com.xxmukulxx.to_dolist.R
import com.xxmukulxx.to_dolist.common.utils.toggleDarkMode
import com.xxmukulxx.to_dolist.databinding.ActivityMainBinding
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject


@AndroidEntryPoint
class MainActivity : BaseActivity() {

    @Inject
    lateinit var dataStoreViewModel: DataStoreViewModel
    private lateinit var binding: ActivityMainBinding
    override val layoutResId: Int
        get() = R.layout.activity_main

    override fun onLayoutCreated() {
        binding = getBinding() as ActivityMainBinding
        toggleDarkMode(dataStoreViewModel)
    }
}