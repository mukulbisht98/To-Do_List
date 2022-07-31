package com.xxmukulxx.to_dolist.feature_insert_todo.presentation.fragment

import androidx.fragment.app.viewModels
import com.xxmukulxx.to_dolist.R
import com.xxmukulxx.to_dolist.common.base.BaseFragment
import com.xxmukulxx.to_dolist.common.utils.hideKeyboard
import com.xxmukulxx.to_dolist.common.utils.showKeyboard
import com.xxmukulxx.to_dolist.databinding.FragInsertTodoBinding
import com.xxmukulxx.to_dolist.feature_insert_todo.presentation.vm.InsertTodoViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class InsertTodoFragment(override val layoutResId: Int = R.layout.frag_insert_todo) :
    BaseFragment() {
    private lateinit var binding: FragInsertTodoBinding

    private val viewModel: InsertTodoViewModel by viewModels()

    override fun onCreateView() {
        initBindings()
        initViewModel()
    }

    private fun initBindings() {
        binding = getBinding() as FragInsertTodoBinding
        binding.viewModel = viewModel
        binding.lifecycleOwner = viewLifecycleOwner
    }

    private fun initViewModel() {
        viewModel.apply {
            b = binding
            setAppBar(getString(R.string.insert_todo))
            isKeyboardOpen.observe(requireActivity()) {
                if (it) hideKeyboard()
                else showKeyboard()
            }
        }
    }
}