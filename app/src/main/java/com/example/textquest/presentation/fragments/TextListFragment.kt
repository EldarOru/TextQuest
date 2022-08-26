package com.example.textquest.presentation.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.textquest.presentation.adapters.MainAdapter
import com.example.textquest.presentation.viewmodels.MainViewModel
import com.example.textquest.ProvideViewModel
import com.example.textquest.databinding.TextListBinding

class TextListFragment: BaseFragment<TextListBinding>() {

    private lateinit var viewModel: MainViewModel
    private lateinit var recyclerView: RecyclerView
    private lateinit var mainAdapter: MainAdapter

    override fun initBinding(inflater: LayoutInflater, container: ViewGroup?): TextListBinding =
        TextListBinding.inflate(inflater, container, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setRecyclerView()
        viewModel = (activity?.application as ProvideViewModel).provideViewModel()

        viewModel.observe(this) {
            it.getStory().apply {
                mainAdapter.submitList(this)
            }
            recyclerView.smoothScrollToPosition(it.getStory().size-1)
        }
    }

    private fun setRecyclerView() {
        recyclerView = binding.textRv
        recyclerView.layoutManager = LinearLayoutManager(context).apply {
            reverseLayout = false
            stackFromEnd = true
        }
        mainAdapter = MainAdapter()
        recyclerView.adapter = mainAdapter
    }
}