package com.example.rickmorty.presentation.detail

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.rickmorty.domain.usecases.GetCharacterByIdUseCase
import com.example.rickmorty.domain.usecases.GetEpisodesByIdsUseCase
import com.example.rickmorty.presentation.common.UiState
import com.example.rickmorty.presentation.model.CharacterDetailUi
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class CharacterDetailViewModel(
    private   val characterId : Int ,
    private val getCharacterByIdUseCase: GetCharacterByIdUseCase,
    private val getEpisodesByIdsUseCase: GetEpisodesByIdsUseCase
) : ViewModel() {

    private val _state = MutableStateFlow<UiState<CharacterDetailUi>>(UiState.Loading)
    val state = _state.asStateFlow()

    init {
        load()
    }


    fun load() {
        viewModelScope.launch {
            _state.value = UiState.Loading

            val characterResult = getCharacterByIdUseCase(characterId)


            characterResult.fold(
                ifLeft = { failure ->
                    _state.value = UiState.Error(failure.message ?: "Ошибка загрузки")
                },
                ifRight = { character ->

                    val episodeIds = character.episode
                        .mapNotNull { it?.substringAfterLast("/")?.toIntOrNull() }
                        .take(20)

                    val episodesResult = getEpisodesByIdsUseCase(episodeIds)

                    episodesResult.fold(
                        ifLeft = { failure ->
                            _state.value = UiState.Error(failure.message ?: "Ошибка загрузки эпизодов")
                        },
                        ifRight = { episodes ->
                            _state.value = UiState.Success(
                                CharacterDetailUi(character = character, episodes = episodes)
                            )
                        }
                    )
                }

            )
        }
    }
}
