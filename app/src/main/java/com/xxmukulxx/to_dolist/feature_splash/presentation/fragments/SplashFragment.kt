package com.xxmukulxx.to_dolist.feature_splash.presentation.fragments

import androidx.fragment.app.viewModels
import com.xxmukulxx.to_dolist.common.base.BaseFragment
import com.xxmukulxx.to_dolist.R
import com.xxmukulxx.to_dolist.databinding.FragSplashBinding
import com.xxmukulxx.to_dolist.feature_splash.presentation.vm.SplashViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SplashFragment(override val layoutResId: Int = R.layout.frag_splash) : BaseFragment() {

    private val viewModel: SplashViewModel by viewModels()
    private lateinit var b: FragSplashBinding

    override fun onCreateView() {
        initBindingsAndViewModel()
        viewModel.initAnimator(b)
    }

    private fun initBindingsAndViewModel() {
        b = getBinding() as FragSplashBinding
    }
}