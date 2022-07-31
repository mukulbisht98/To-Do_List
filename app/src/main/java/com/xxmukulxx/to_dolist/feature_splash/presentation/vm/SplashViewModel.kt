package com.xxmukulxx.to_dolist.feature_splash.presentation.vm

import android.animation.Animator
import com.xxmukulxx.to_dolist.common.base.BaseViewModel
import com.xxmukulxx.to_dolist.R
import com.xxmukulxx.to_dolist.common.utils.navigateFromSplash
import com.xxmukulxx.to_dolist.databinding.FragSplashBinding
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class SplashViewModel @Inject constructor(
) : BaseViewModel() {

    fun initAnimator(b: FragSplashBinding) {
        b.anim.addAnimatorListener(object : Animator.AnimatorListener {
            override fun onAnimationStart(p0: Animator?) {}

            override fun onAnimationEnd(p0: Animator?) {
                proceed(b)
                
            }

            override fun onAnimationCancel(p0: Animator?) {}

            override fun onAnimationRepeat(p0: Animator?) {}
        })
    }

    fun proceed(b: FragSplashBinding) {
        b.root.navigateFromSplash(R.id.action_splashFragment_to_mainFragment, null)
    }
}