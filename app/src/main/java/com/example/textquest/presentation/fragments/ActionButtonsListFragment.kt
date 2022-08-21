package com.example.textquest.presentation.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.textquest.presentation.viewmodels.MainViewModel
import com.example.textquest.ProvideViewModel
import com.example.textquest.databinding.ActionButtonsListBinding

class ActionButtonsListFragment: BaseFragment<ActionButtonsListBinding>() {

    private lateinit var viewModel: MainViewModel

    override fun initBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ): ActionButtonsListBinding = ActionButtonsListBinding.inflate(inflater, container, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        val actionLayout = binding.actionLayout
        viewModel = (activity?.application as ProvideViewModel).provideViewModel()

        viewModel.observe(this) {
            it.getStory()[it.getStoryCount()].showActionButtons(context = requireContext(), linearLayout = actionLayout)
        }
    }
}