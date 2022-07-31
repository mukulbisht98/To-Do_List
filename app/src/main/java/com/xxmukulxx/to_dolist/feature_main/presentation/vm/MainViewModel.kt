package com.xxmukulxx.to_dolist.feature_main.presentation.vm

import android.view.View
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import com.xxmukulxx.to_dolist.R
import com.xxmukulxx.to_dolist.common.base.BaseViewModel
import com.xxmukulxx.to_dolist.common.utils.hide
import com.xxmukulxx.to_dolist.common.utils.navigateWithViewModel
import com.xxmukulxx.to_dolist.common.utils.show
import com.xxmukulxx.to_dolist.common.utils.toast
import com.xxmukulxx.to_dolist.databinding.FragMainBinding
import com.xxmukulxx.to_dolist.feature_todo.domain.TodoUseCases
import com.xxmukulxx.to_dolist.feature_todo.domain.model.TodoData
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import java.util.*
import javax.inject.Inject
import kotlin.concurrent.schedule

@HiltViewModel
class MainViewModel @Inject constructor(var useCases: TodoUseCases) :
    BaseViewModel() {
    lateinit var b: FragMainBinding
    lateinit var todoListLiveData: LiveData<List<TodoData>>

    init {
        viewModelScope.launch {
            todoListLiveData = useCases.getTodoList().asLiveData(this.coroutineContext)
        }
    }

    var isAppBarExpanded: MutableLiveData<Boolean> = MutableLiveData(false)
    var flag = false

    fun setAppBar(title: String) {
        if (title.isBlank()) {
            return b.topBar.appBar.hide()
        }
        b.topBar.apply {
            appBar.show()
            tvTitle.text = title
            ivLeftButton.hide()
            ivRightButton.setImageResource(R.drawable.ic_search)
            ivRightButton.setOnClickListener {
                isAppBarExpanded.value?.let {
                    isAppBarExpanded.postValue(!it)
                }
            }
        }
    }

    fun insertTodoFrag(v: View) =
        v.navigateWithViewModel(R.id.action_mainFragment_to_insertTodoFragment)

    fun todoItemClicked(index: Int) {
        if (flag) {
            viewModelScope.launch {
                todoListLiveData.value?.get(index).let {
                    if (it != null) {
                        useCases.deleteTodo(it.id)
                    }
                }
            }
        } else {
            toast("Click again to delete.")
            flag = true
            Timer().schedule(2000) {
                flag = false
            }
        }
    }
}