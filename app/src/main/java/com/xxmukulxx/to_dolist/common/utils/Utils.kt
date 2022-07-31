package com.xxmukulxx.to_dolist.common.utils

import android.app.Activity
import android.content.Context
import android.net.Uri
import android.os.Bundle
import android.view.View
import android.view.inputmethod.InputMethodManager
import android.widget.ImageView
import android.widget.Toast
import androidx.appcompat.app.AppCompatDelegate
import androidx.fragment.app.Fragment
import androidx.fragment.app.findFragment
import androidx.navigation.NavDirections
import androidx.navigation.NavOptions
import androidx.navigation.Navigator
import androidx.navigation.findNavController
import androidx.navigation.fragment.NavHostFragment
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.CenterCrop
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import com.xxmukulxx.to_dolist.MyApplication.AppContext.appContext
import com.xxmukulxx.to_dolist.R
import com.xxmukulxx.to_dolist.common.data.data_store.vm.DataStoreViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

// NavigationComponents Utils
fun View.navigateWithId(id: Int, bundle: Bundle? = null, extras: Navigator.Extras? = null) = try {
    this.findNavController().navigate(id, bundle, null, extras)
} catch (e: Exception) {
    e.printStackTrace()
}

fun View.navigateFromSplash(id: Int, bundle: Bundle? = null, extras: Navigator.Extras? = null) =
    try {
        this.findNavController().navigate(
            id, bundle, NavOptions.Builder()
                .setPopUpTo(
                    R.id.splashFragment,
                    true
                ).build(), extras
        )
    } catch (e: Exception) {
        e.printStackTrace()
    }

fun View.navigateWithAction(action: NavDirections) = try {
    this.findNavController().navigate(action)
} catch (e: Exception) {
    e.printStackTrace()
}

fun View.navigateWithViewModel(id: Int) = try {
    NavHostFragment.findNavController(this.findFragment()).navigate(id)
} catch (e: Exception) {
    e.printStackTrace()
}

fun View.navigateBack() = try {
    this.findNavController().navigateUp()
} catch (e: Exception) {
    e.printStackTrace()
}

fun getNavOptions(): NavOptions {
    return NavOptions.Builder()
        .setEnterAnim(R.anim.back_pop_exit)
        .setExitAnim(R.anim.login_pop_back)
        .build()
}

// Visibility Utils
fun View.hide() {
    this.visibility = View.GONE
}

fun View.show() {
    this.visibility = View.VISIBLE
}

fun View.invisible() {
    this.visibility = View.INVISIBLE
}

// DarkMode Utils
fun toggleDarkMode(dataStoreViewModel: DataStoreViewModel) {
    CoroutineScope(Dispatchers.Main).launch {
        dataStoreViewModel.readFromLocal.collect {
            when (it) {
                1 -> AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
                2 -> AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES)
                3 -> AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_FOLLOW_SYSTEM)
                else -> dataStoreViewModel.saveToLocal(3)
            }
        }
    }
}

// Toast Utils
fun toast(message: String) {
    Toast.makeText(appContext, message, Toast.LENGTH_SHORT).show()
}


// String Utils
fun String.capitalize(): String {
    return this.first().uppercase() + this.substring(1)
}

// Resources Utils
fun getString(id: Int): String {
    return appContext.getString(id)
}

//Color Utils
fun getColor(id: Int): Int {
    return appContext.getColor(id)
}

//Dimen Utils
fun getDimen(id: Int): Float {
    return appContext.resources.getDimension(id)
}

// Glide Utils
fun ImageView.setImgProfile(url: String?) {
    if (url.isNullOrEmpty()) {
        Glide.with(appContext).load(R.drawable.ic_profile)
            .circleCrop()
            .into(this)
    } else {
        Glide.with(appContext).load(url)
            .error(R.drawable.ic_profile)
            .thumbnail(Glide.with(appContext).load(R.drawable.loader_gif).circleCrop())
            .placeholder(R.drawable.ic_profile)
            .circleCrop()
            .into(this)
    }
}

fun ImageView.setImg(url: String?) {
    if (url.isNullOrEmpty()) {
        Glide.with(appContext).load(R.drawable.ic_image_placeholder)
            .into(this)
    } else {
        Glide.with(appContext).load(url).transform(CenterCrop())
            .error(R.drawable.ic_image_placeholder)
            .thumbnail(Glide.with(appContext).load(R.drawable.loader_gif))
            .placeholder(R.drawable.ic_image_placeholder)
            .into(this)
    }
}

fun ImageView.setImgFromUri(uri: Uri) {
    Glide.with(appContext).load(uri)
        .error(R.drawable.ic_image_placeholder)
        .thumbnail(Glide.with(appContext).load(R.drawable.loader_gif))
        .placeholder(R.drawable.ic_image_placeholder)
        .into(this)
}

fun ImageView.setImgWithRadius(url: String?, radius: Int) {
    if (url.isNullOrEmpty()) {
        Glide.with(appContext).load(R.drawable.ic_image_placeholder)
            .into(this)
    } else {
        Glide.with(appContext).load(url).transform(CenterCrop(), RoundedCorners(radius))
            .error(R.drawable.ic_image_placeholder)
            .thumbnail(Glide.with(appContext).load(R.drawable.loader_gif))
            .placeholder(R.drawable.ic_image_placeholder)
            .into(this)
    }
}

// keyboard
fun Fragment.hideKeyboard() {
    view?.let { activity?.hideKeyboard(it) }
}

fun Activity.hideKeyboard() {
    hideKeyboard(currentFocus ?: View(this))
}

fun Context.hideKeyboard(view: View) {
    val inputMethodManager = getSystemService(Activity.INPUT_METHOD_SERVICE) as InputMethodManager
    inputMethodManager.hideSoftInputFromWindow(view.windowToken, 0)
}

fun Fragment.showKeyboard() {
    view?.let { activity?.showKeyboard(it) }
}

fun Activity.showKeyboard() {
    showKeyboard(currentFocus ?: View(this))
}

fun Context.showKeyboard(view: View) {
    val inputMethodManager = getSystemService(Activity.INPUT_METHOD_SERVICE) as InputMethodManager
    inputMethodManager.showSoftInput(view, 0)
}