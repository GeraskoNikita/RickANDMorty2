package com.example.rickmorty.domain.usecases

import com.example.rickmorty.data.common.Either
import com.example.rickmorty.data.common.Failure
import com.example.rickmorty.domain.model.Character
import com.example.rickmorty.domain.repository.CharacterRepository

class GetCharacterByIdUseCase(
    private val repository: CharacterRepository
) {
    suspend operator fun invoke(id: Int): Either<Failure, Character> = repository.getCharacterById(id)
}
