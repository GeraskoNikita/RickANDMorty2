package com.example.rickmorty.presentation.detail

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.LinearLayoutManager
import coil.load
import com.example.rickmorty.R
import com.example.rickmorty.databinding.FragmentCharacterDetailBinding
import com.example.rickmorty.presentation.adapter.EpisodesAdapter
import com.example.rickmorty.presentation.common.BaseFragment
import com.example.rickmorty.presentation.common.handleState
import org.koin.androidx.viewmodel.ext.android.viewModel
import org.koin.core.parameter.parametersOf
import kotlin.getValue

class CharacterDetailFragment :
    BaseFragment<FragmentCharacterDetailBinding>(FragmentCharacterDetailBinding::inflate) {

    private val args: CharacterDetailFragmentArgs by navArgs()

    private val viewModel: CharacterDetailViewModel by viewModel{  parametersOf(args.id)}

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        val binding = FragmentCharacterDetailBinding.bind(view)
        val episodesAdapter = EpisodesAdapter()
        binding.rvEpisodes.adapter = episodesAdapter
        binding.rvEpisodes.layoutManager = LinearLayoutManager(requireContext())




        collectState(viewModel.state) { state ->
            handleState(
                state = state,
                progress = binding.progress,
                onSuccess = { ui ->
                    val c = ui.character
                    binding.avatar.load(c.image)
                    binding.name.text = c.name
                    binding.status.text = "${c.status} - ${c.species}"
                    binding.gender.text = c.gender
                    binding.location.text = c.location.name
                    binding.firstSeen.text = ui.firstSeenName
                    episodesAdapter.submit(ui.episodes)
                },
                onError = {
                    Toast.makeText(requireContext(), it, Toast.LENGTH_SHORT).show()
                }
            )
        }
    }

}
