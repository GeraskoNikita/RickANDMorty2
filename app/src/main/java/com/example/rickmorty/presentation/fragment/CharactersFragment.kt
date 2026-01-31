package com.example.rickmorty.presentation.characters

import android.os.Bundle
import androidx.lifecycle.lifecycleScope
import androidx.paging.LoadState
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import android.view.View
import android.widget.Toast
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController

import com.example.rickmorty.R
import com.example.rickmorty.databinding.FragmentCharactersBinding
import com.example.rickmorty.presentation.common.BaseFragment
import org.koin.androidx.viewmodel.ext.android.viewModel

class CharactersFragment :   BaseFragment<FragmentCharactersBinding>(FragmentCharactersBinding::inflate) {

    private val viewModel: CharactersViewModel by viewModel()

    private var adapter: CharactersAdapter = CharactersAdapter({})

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        val binding = FragmentCharactersBinding.bind(view)

        adapter = CharactersAdapter(onClick = { id ->
            val action = CharactersFragmentDirections.actionCharactersToDetail(id)
            navigate(action)

        })
//        val adapter = CharactersAdapter { id ->
//            val bundle = Bundle().apply { putInt("id", id) }
//            findNavController().navigate(R.id.action_characters_to_detail, bundle)
//        }


        binding.recycler.adapter = adapter

        viewLifecycleOwner.lifecycleScope.launch {
            viewModel.characters.collectLatest { pagingData ->
                adapter.submitData(pagingData)
            }
        }

        viewLifecycleOwner.lifecycleScope.launch {
            adapter.loadStateFlow.collectLatest { state ->
                val isLoading = state.refresh is LoadState.Loading
                val isError = state.refresh is LoadState.Error

                binding.progress.isVisible = isLoading
                binding.recycler.isVisible = !isLoading

                if (isError) {
                    val e = (state.refresh as LoadState.Error).error
                    Toast.makeText(
                        requireContext(),
                        e.message ?: "Ошибка загрузки",
                        Toast.LENGTH_SHORT
                    ).show()
                }
            }
        }
    }

}

