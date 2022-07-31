package com.xxmukulxx.to_dolist.feature_insert_todo.presentation.vm

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.xxmukulxx.to_dolist.R
import com.xxmukulxx.to_dolist.common.base.BaseViewModel
import com.xxmukulxx.to_dolist.common.presentation.adapter.RecyclerAdapter
import com.xxmukulxx.to_dolist.common.utils.getColor
import com.xxmukulxx.to_dolist.common.utils.hide
import com.xxmukulxx.to_dolist.common.utils.navigateBack
import com.xxmukulxx.to_dolist.common.utils.show
import com.xxmukulxx.to_dolist.databinding.FragInsertTodoBinding
import com.xxmukulxx.to_dolist.feature_todo.domain.TodoUseCases
import com.xxmukulxx.to_dolist.feature_todo.domain.model.TodoData
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import java.text.SimpleDateFormat
import java.util.*
import javax.inject.Inject


@HiltViewModel
class InsertTodoViewModel @Inject constructor(private val useCases: TodoUseCases) :
    BaseViewModel() {

    lateinit var b: FragInsertTodoBinding

    private val list = mutableListOf(
        getColor(R.color.dot_1),
        getColor(R.color.dot_2),
        getColor(R.color.dot_3),
        getColor(R.color.dot_4),
        getColor(R.color.dot_5),
        getColor(R.color.dot_6),
        getColor(R.color.dot_7),
        getColor(R.color.dot_8),
        getColor(R.color.dot_9),
        getColor(R.color.dot_10),
        getColor(R.color.dot_11),
        getColor(R.color.dot_12),
        getColor(R.color.dot_13),
        getColor(R.color.dot_14),
        getColor(R.color.dot_15),
    )
    var adapter = RecyclerAdapter(list, R.layout.item_color_dot) { colorClicked(list[it]) }
    var isAppBarExpanded: MutableLiveData<Boolean> = MutableLiveData(false)
    var isKeyboardOpen: MutableLiveData<Boolean> = MutableLiveData(false)

    private val timeMillis = System.currentTimeMillis()
    var dateString = SimpleDateFormat("dd/MM/yyyy hh:mm aa", Locale.getDefault()).format(timeMillis)!!
    private val title: MutableLiveData<String> = MutableLiveData("")
    private val date: MutableLiveData<Long> = MutableLiveData(timeMillis)
    private val color: MutableLiveData<Int> = MutableLiveData(getColor(R.color.dot_1))
    private val content: MutableLiveData<String> = MutableLiveData("")

    fun onTitleChange(s: CharSequence, start: Int, before: Int, count: Int) {
        if (s.toString().isNotBlank())
            title.postValue(s.trim().toString())
    }

    fun onContentChange(s: CharSequence, start: Int, before: Int, count: Int) {
        if (s.toString().isNotBlank())
            content.postValue(s.trim().toString())
    }

    fun setAppBar(title: String) {
        if (title.isBlank()) {
            return b.topBar.appBar.hide()
        }
        b.topBar.apply {
            appBar.show()
            tvTitle.text = title
            ivLeftButton.setOnClickListener {
                saveItem()
                b.root.navigateBack()
            }
            ivRightButton.apply {
                setImageResource(R.drawable.vec_circle)
                setColorFilter(getColor(R.color.dot_1))
                setOnClickListener {
                    isAppBarExpanded.value?.let {
                        isAppBarExpanded.postValue(!it)
                    }
                }
            }
        }
        b.etContent.requestFocus()
    }

    private fun saveItem() {
        title.value?.let { title ->
            date.value?.let { date ->
                color.value?.let { color ->
                    content.value?.let { content ->
                        val data = TodoData(title, content, date, color)
                        if (title.isNotBlank() || content.isNotBlank())
                            viewModelScope.launch {
                                useCases.insertTodo(data)
                            }
                    }
                }
            }
        }
    }

    private fun colorClicked(value: Int) {
        b.topBar.ivRightButton.setColorFilter(value)
        color.postValue(value)
        isAppBarExpanded.postValue(false)
    }
}