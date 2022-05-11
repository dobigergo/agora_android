package com.example.agora.ui.main

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import com.example.agora.databinding.FragmentMainBinding

class MainFragment : Fragment() {

    private val viewModel: MainViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val binding = FragmentMainBinding.inflate(inflater)

        // Allows Data Binding to Observe LiveData with the lifecycle of this Fragment
        binding.lifecycleOwner = this

        // Giving the binding access to the OverviewViewModel
        binding.viewModel = viewModel

        viewModel.status.observe(viewLifecycleOwner
        ) { newValue -> binding.message.text = newValue.toString() }
        viewModel.temp.observe(viewLifecycleOwner
        ) { newValue -> binding.message2.text = newValue.toString() }
        viewModel.humidity.observe(viewLifecycleOwner
        ) { newValue -> binding.message3.text = newValue.toString() }

        return binding.root
        }
    }