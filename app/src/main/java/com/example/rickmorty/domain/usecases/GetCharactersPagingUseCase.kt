package com.example.rickmorty.domain.usecases

import androidx.paging.PagingData
import com.example.rickmorty.domain.model.Character
import com.example.rickmorty.domain.repository.CharacterRepository
import kotlinx.coroutines.flow.Flow

class GetCharactersPagingUseCase(
    private val repository: CharacterRepository
) {
    operator fun invoke(): Flow<PagingData<Character>> = repository.getCharactersPaging()
}
