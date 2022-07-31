package com.xxmukulxx.to_dolist.feature_main.presentation.fragment

import androidx.fragment.app.viewModels
import com.xxmukulxx.to_dolist.R
import com.xxmukulxx.to_dolist.common.base.BaseFragment
import com.xxmukulxx.to_dolist.common.presentation.adapter.RecyclerAdapter
import com.xxmukulxx.to_dolist.common.utils.hide
import com.xxmukulxx.to_dolist.databinding.FragMainBinding
import com.xxmukulxx.to_dolist.feature_main.presentation.vm.MainViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainFragment(override val layoutResId: Int = R.layout.frag_main) : BaseFragment() {

    private lateinit var binding: FragMainBinding
    private val viewModel: MainViewModel by viewModels()

    override fun onCreateView() {
        initBindings()
        initViewModel()
    }

    private fun initBindings() {
        binding = getBinding() as FragMainBinding
        binding.viewModel = viewModel
        binding.lifecycleOwner = viewLifecycleOwner
    }

    private fun initViewModel() {
        viewModel.apply {
            b = binding
            todoListLiveData.observe(requireActivity()) { list ->
                if (list.isNotEmpty()) binding.tvNoItemToDisplay.hide()
                binding.rvMainList.adapter = RecyclerAdapter(list.toMutableList(), R.layout.item_todo_list) {
                    this.todoItemClicked(it)
                }
            }
            setAppBar(getString(R.string.app_name))
        }
    }

}