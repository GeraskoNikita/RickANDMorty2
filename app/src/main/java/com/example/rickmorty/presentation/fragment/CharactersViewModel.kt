package com.example.rickmorty.presentation.characters

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.example.rickmorty.domain.model.Character
import com.example.rickmorty.domain.usecases.GetCharactersPagingUseCase
import kotlinx.coroutines.flow.Flow

class CharactersViewModel(
    getCharactersPagingUseCase: GetCharactersPagingUseCase
) : ViewModel() {

    val characters: Flow<PagingData<Character>> =
        getCharactersPagingUseCase().cachedIn(viewModelScope)
}
